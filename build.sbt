import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := "realworld-scala-play"
organization := "com.melalex"
version := "1.0-SNAPSHOT"

scalaVersion := "2.13.3"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalafixScalaBinaryVersion := "2.13.3"

scalafmtOnCompile := false
scalafixOnCompile := false

dockerBaseImage := "adoptopenjdk/openjdk14:jre-14.0.2_12-alpine"
dockerExposedPorts := List(8080)
dockerRepository := Some("docker.pkg.github.com")
dockerUsername := Some("melalex")
dockerAlias := DockerAlias(dockerRepository.value, dockerUsername.value, s"${name.value}/realworld-scala-play-api", Some(version.value))
dockerUpdateLatest := true
dockerCommands ++= List(Cmd("USER", "root"), ExecCmd("RUN", "apk", "add", "--no-cache", "bash"))

addCompilerPlugin(scalafixSemanticdb)
enablePlugins(JavaAppPackaging, DockerPlugin)

libraryDependencies ++= {

  // Dependencies versions
  val macWireVersion       = "2.3.7"
  val scalaTestPlayVersion = "5.0.0"
  val scalaMockVersion     = "5.0.0"
  val slickVersion         = "5.0.0"

  List(
    // Play
    ws,
    // DI
    "com.softwaremill.macwire" %% "macros" % macWireVersion,
    // DB
    "com.typesafe.play" %% "play-slick"            % slickVersion,
    "com.typesafe.play" %% "play-slick-evolutions" % slickVersion,
    // Test
    "org.scalamock"          %% "scalamock"          % scalaMockVersion     % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % scalaTestPlayVersion % Test
  )
}
