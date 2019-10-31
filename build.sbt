name := "spark-hbase"

version := "0.1"

version := "0.1"

scalaVersion := "2.11.8"


libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "3.0.0-cdh6.2.0"
libraryDependencies += "org.apache.hbase" % "hbase" % "2.1.0-cdh6.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-server" % "2.1.0-cdh6.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-mapreduce" % "2.1.0-cdh6.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-client" % "2.1.0-cdh6.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-common" % "2.1.0-cdh6.2.0"
libraryDependencies += "org.apache.hbase" % "hbase-spark" % "2.1.0-cdh6.2.0"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.0-cdh6.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.0-cdh6.2.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.0-cdh6.2.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.0-cdh6.2.0"
libraryDependencies += "org.apache.zookeeper" % "zookeeper" % "3.4.14"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.6.5"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.6.5"

libraryDependencies += "org.scala-lang" % "scala-xml" % "2.11.0-M4"


resolvers += "Cloudera's CDH3 Maven repo" at "https://repository.cloudera.com/artifactory/cloudera-repos/"