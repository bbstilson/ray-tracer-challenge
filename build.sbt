val scala213Version = "2.13.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "ray-tracer-challenge",
    version := "0.0.1",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0",
      "org.scalactic" %% "scalactic" % "3.1.1",
      "org.scalatest" %% "scalatest" % "3.1.1" % Test
    ),
    scalaVersion := scala213Version,
    resolvers += "Artima Maven Repository".at("https://repo.artima.com/releases"),
    scalacOptions ++= Seq(
      "-deprecation"
    )
  )
