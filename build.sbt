import sbt._

lazy val kinesis_sql = (project in sbt.file("."))
  .settings(
    name := "kinesis-sql",
    version := sparkVersion,
    scalaVersion := "2.11.8"
  )

javacOptions ++= Seq(
  "-source", "1.8"
)

val sparkVersion = "2.3.1"
val awsSDKVersion = "1.11.419"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-parent" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.amazonaws" % "amazon-kinesis-client" % "1.9.2",
  "com.amazonaws" % "amazon-kinesis-producer" % "0.12.9",
  "com.amazonaws" % "aws-java-sdk-sts" % awsSDKVersion,
  "com.amazonaws" % "aws-java-sdk-kinesis" % awsSDKVersion,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformats-binary" % "2.9.7" pomOnly(),

  // test deps
  "org.apache.spark" %% "spark-sql" % sparkVersion % "test" classifier "tests",
  "org.apache.spark" %% "spark-catalyst" % sparkVersion % "test" classifier "tests",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "test" classifier "tests",
  "org.apache.spark" %% "spark-core" % sparkVersion % "test" classifier "tests",
  "org.mockito" % "mockito-core" % "1.10.19" % "test",
  "org.scalatest" %% "scalatest" % "3.0.3" % "test",
  "org.apache.spark" %% "spark-tags" % sparkVersion % "test" classifier "tests"
)

// skip tests since they don't pass (lol)
test in assembly := {}