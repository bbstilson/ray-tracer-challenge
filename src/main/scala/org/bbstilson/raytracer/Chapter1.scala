package org.bbstilson.raytracer

import org.bbstilson.raytracer.models.{ Point, Vector }

case class Projectile(position: Point, velocity: Vector)
case class Environment(gravity: Vector, wind: Vector)

object Chapter1 {
  def main(args: Array[String]): Unit = {
    val e = Environment(Vector(0,-0.1,0), Vector(-0.01,0,0))
    var p = Projectile(Point(0,1,0), Vector(1,1,0).normalize)

    while(p.position.y > 0) {
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
