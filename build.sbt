import play.PlayImport.PlayKeys._
import scoverage.ScoverageSbtPlugin.ScoverageKeys._

name := "splashpage"

scalaVersion in ThisBuild := "2.11.7"

// required because of issue between scoverage & sbt
parallelExecution in Test in ThisBuild := true

lazy val generated = project
  .in(file("generated"))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      ws
    )
  )

lazy val api = project
  .in(file("api"))
  .dependsOn(generated)
  .aggregate(generated)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    routesImport += "io.flow.splashpage.v0.Bindables._",
    libraryDependencies ++= Seq(
      ws,
      jdbc,
      "com.typesafe.play" %% "anorm" % "2.4.0",
      "org.postgresql" % "postgresql" % "9.4-1202-jdbc42",
      "org.scalatestplus" %% "play" % "1.4.0-M4" % "test"
    )
  )

lazy val www = project
  .in(file("www"))
  .dependsOn(generated)
  .aggregate(generated)
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    routesImport += "io.flow.splashpage.v0.Bindables._",
    libraryDependencies ++= Seq(
      "org.webjars" %% "webjars-play" % "2.4.0-1",
      "org.webjars" % "bootstrap" % "3.3.5",
      "org.webjars" % "jquery" % "2.1.4"
    )
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name <<= name("splashpage-" + _),
  libraryDependencies ++= Seq(
    specs2 % Test,
    "org.scalatest" %% "scalatest" % "2.2.0" % Test
  ),
  scalacOptions += "-feature",
  coverageHighlighting := true,
  resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
)
