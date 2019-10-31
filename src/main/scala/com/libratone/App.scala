package com.libratone

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Put, Result}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapred.TableOutputFormat
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.mapred.JobConf
import org.apache.spark.{SparkConf, SparkContext}

object App {

  def main(args: Array[String]): Unit = {
    
    val conf = new SparkConf().setMaster("local").setAppName("hbase")
    val sc = new SparkContext(conf)

    val hbaseTableName = "test"
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set("hbase.zookeeper.quorum", "master")
    hbaseConf.set("hbase.zookeeper.property.clientPort", "2181")
    hbaseConf.set(TableInputFormat.INPUT_TABLE, hbaseTableName)
    hbaseConf.set(TableOutputFormat.OUTPUT_TABLE, hbaseTableName)
    val jobConf = new JobConf(hbaseConf)
    jobConf.setOutputFormat(classOf[TableOutputFormat])

    /*数据写入*/
    val indataRdd = sc.makeRDD(Array("11,home1,sku1", "22,home2,sku2"))
    val rdd = indataRdd.map(_.split(",")).map(array => {
      val put = new Put(Bytes.toBytes(array(0)))
      put.addColumn("pv".getBytes, "sku".getBytes, array(1).getBytes)
      put.addColumn("pv".getBytes, "page".getBytes, array(2).getBytes)
      (new ImmutableBytesWritable, put)
    })
    rdd.saveAsHadoopDataset(jobConf)

    /*数据读取*/
    val hbaseRdd = sc.newAPIHadoopRDD(hbaseConf, classOf[TableInputFormat], classOf[ImmutableBytesWritable], classOf[Result])
    hbaseRdd.foreach {
      case (_, result: Result) =>
        val key = Bytes.toString(result.getRow)
        val page = Bytes.toString(result.getValue("pv".getBytes, "page".getBytes))
        val sku = Bytes.toString(result.getValue("pv".getBytes, "sku".getBytes))
        System.out.println(s"reuslt key:" + key + "\tpage:" + page + "\tsku:" + sku)
    }
    sc.stop()
  }
}
