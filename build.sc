import mill._, scalalib._
import coursier.maven.MavenRepository

object raytracerchallenge extends ScalaModule {
  def scalaVersion = "2.13.1"

  def scalacOptions = Seq("-deprecation")

  def ivyDeps = Agg(
    ivy"org.scala-lang.modules::scala-parallel-collections:0.2.0"
  )

  object test extends Tests {

    def ivyDeps = Agg(
      ivy"org.scalactic::scalactic:3.1.1",
      ivy"org.scalatest::scalatest:3.1.1"
    )

    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}
