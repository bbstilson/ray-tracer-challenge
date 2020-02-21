val dottyVersion = "0.22.0-RC1"
val scala213Version = "2.13.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "ray-tracer-challenge",
    version := "0.0.1",

    libraryDependencies ++= Seq(
      "dev.travisbrown" %% "scalactic" % "3.1.0-20200201-c4c847f-NIGHTLY",
      "dev.travisbrown" %% "scalatest" % "3.1.0-20200201-c4c847f-NIGHTLY" % Test
    ),
    // libraryDependencies := libraryDependencies.value.map(_.withDottyCompat(scalaVersion.value)),

    // To make the default compiler and REPL use Dotty
    scalaVersion := dottyVersion,

    // To cross compile with Dotty and Scala 2
    crossScalaVersions := Seq(dottyVersion, scala213Version),

    scalacOptions ++= Seq(
      "-language:implicitConversions"
    )
  )
