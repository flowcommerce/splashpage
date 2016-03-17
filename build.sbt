import play.PlayImport.PlayKeys._
import scoverage.ScoverageSbtPlugin.ScoverageKeys._

name := "splashpage"

organization := "io.flow"

scalaVersion in ThisBuild := "2.11.8"

// required because of issue between scoverage & sbt
parallelExecution in Test in ThisBuild := true

lazy val api = project
  .in(file("api"))
  .enablePlugins(PlayScala)
  .settings(commonSettings: _*)
  .settings(
    routesImport += "io.flow.splashpage.v0.Bindables._",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      jdbc,
      "io.flow" %% "lib-play" % "0.0.51",
      "io.flow" %% "lib-postgresql" % "0.0.20",
      "org.postgresql" % "postgresql" % "9.4.1208",
      "com.typesafe.play" %% "anorm" % "2.5.0"
    )
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name <<= name("splashpage-" + _),
  libraryDependencies ++= Seq(
    specs2 % Test,
    "org.scalatest" %% "scalatest" % "2.2.6" % Test
  ),
  sources in (Compile,doc) := Seq.empty,
  publishArtifact in (Compile, packageDoc) := false,
  scalacOptions += "-feature",
  coverageHighlighting := true,
  resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  resolvers += "Artifactory" at "https://flow.artifactoryonline.com/flow/libs-release/",
  credentials += Credentials(
    "Artifactory Realm",
    "flow.artifactoryonline.com",
    System.getenv("ARTIFACTORY_USERNAME"),
    System.getenv("ARTIFACTORY_PASSWORD")
  )
)
version := "0.1.24"
