package org.bbstilson.raytracer

import org.bbstilson.raytracer.models._

case class Projectile(position: Point, velocity: SceneVector)
case class Environment(gravity: SceneVector, wind: SceneVector)

object Chapter1 {

  def main(args: Array[String]): Unit = {
    val e = Environment(SceneVector(0, -0.1, 0), SceneVector(-0.01, 0, 0))
    var p = Projectile(Point(0, 1, 0), SceneVector(1, 1, 0).normalize)

    while (p.position.y > 0) {
      val nextP = tick(e, p)
      println(nextP)
      p = nextP
    }
  }

  private def tick(env: Environment, proj: Projectile): Projectile = {
    Projectile(
      proj.position + proj.velocity,
      proj.velocity + env.gravity + env.wind
    )
  }
}
