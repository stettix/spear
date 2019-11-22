import sbt._

object Dependencies {

  lazy val arrowDependencies = Seq(
    "arrow-vector", "arrow-memory", "arrow-format", "arrow-flight", "arrow-avro", "arrow-tools",
    "arrow-plasma", "arrow-jdbc", "arrow-algorithm", "arrow-performance", "gandiva", "orc"
  ).map("org.apache.arrow" % _ % "0.15.1")

  lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.8"

}
