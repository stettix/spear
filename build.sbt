import Shared._
import Dependencies._
import com.lucidchart.sbt.scalafmt.ScalafmtCorePlugin.autoImport._

ThisBuild / organization := "net.stettix"
ThisBuild / name := "spear"
ThisBuild / description := "Scala API for Apache Arrows"

lazy val commonSettings = Seq(
  version := "0.0.1",
  licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
  scalaVersion := "2.13.1",
  scalacOptions ++= scala213CompilerFlags,
  scalafmtOnCompile := true,
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1"),
  libraryDependencies ++= Seq(
    scalatest % Test,
    "org.scalacheck" %% "scalacheck" % "1.14.0" % Test
  ),
  resolvers += Resolver.sonatypeRepo("releases"),
  cancelable in Global := true,
  run in Compile := Defaults.runTask(fullClasspath in Compile,
                                     mainClass in (Compile, run),
                                     runner in (Compile, run))
) ++ testSettings

lazy val testSettings = Defaults.itSettings ++ Seq(
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oDF"),
  parallelExecution in IntegrationTest := false,
  fork in IntegrationTest := true
)

lazy val spear = (project in file("."))
  .aggregate(`core`)
  .settings(commonSettings)

lazy val `core` = project
  .in(file("core"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.apache.arrow.gandiva" % "arrow-gandiva" % "0.15.1"
    ))
