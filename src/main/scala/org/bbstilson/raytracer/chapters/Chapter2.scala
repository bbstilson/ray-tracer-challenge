package org.bbstilson.raytracer.chapters

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.view._

object Chapter2 {

  def main(args: Array[String]): Unit = {
    val start = Point(0, 1, 0)
    val velocity = SceneVector(1, 1.8, 0).normalize * 11.25
    var p = Projectile(start, velocity)

    val gravity = SceneVector(0, -0.1, 0)
    val wind = SceneVector(-0.01, 0, 0)
    val e = Environment(gravity, wind)

    val c = new Canvas(900, 550)
    val blue = Color(0, 1, 0.85)

    while (p.position.y > 0) {
      val x = p.position.x.toInt
      // Pixels are upside down, so we correct for that here.
      val y = 550 - p.position.y.toInt
      c.writePixel(x, y, blue)
      val nextP = tick(e, p)
      p = nextP
    }

    c.save
  }

  private def tick(env: Environment, proj: Projectile): Projectile = {
    Projectile(
      proj.position + proj.velocity,
      proj.velocity + env.gravity + env.wind
    )
  }
}
