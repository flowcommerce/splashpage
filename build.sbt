import play.PlayImport.PlayKeys._

name := "splashpage"

organization := "io.flow"

scalaVersion in ThisBuild := "2.11.8"

lazy val api = project
  .in(file("api"))
  .enablePlugins(PlayScala)
  .enablePlugins(NewRelic)
  .settings(commonSettings: _*)
  .settings(
    routesImport += "io.flow.splashpage.v0.Bindables._",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(
      jdbc,
      "io.flow" %% "lib-play" % "0.1.62",
      "io.flow" %% "lib-postgresql-play" % "0.0.5",
      "io.flow" %% "lib-reference" % "0.2.38",
      "org.postgresql" % "postgresql" % "9.4.1210",
      "org.scalatestplus" %% "play" % "1.4.0" % "test"
    )
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name <<= name("splashpage-" + _),
  libraryDependencies ++= Seq(
    specs2 % Test
  ),
  sources in (Compile,doc) := Seq.empty,
  publishArtifact in (Compile, packageDoc) := false,
  scalacOptions += "-feature",
  resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  resolvers += "Artifactory" at "https://flow.artifactoryonline.com/flow/libs-release/",
  credentials += Credentials(
    "Artifactory Realm",
    "flow.artifactoryonline.com",
    System.getenv("ARTIFACTORY_USERNAME"),
    System.getenv("ARTIFACTORY_PASSWORD")
  )
)
