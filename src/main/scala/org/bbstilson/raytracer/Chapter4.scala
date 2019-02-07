package org.bbstilson.raytracer

import org.bbstilson.raytracer.models._

import Math.PI

object Chapter4 {
  def main(args: Array[String]): Unit = {
    val width, height = 240
    val radius = width * (3d / 8)
    val center = width / 2

    val canvas = new Canvas(width, height)
    val white = Color(1,1,1)
    val midnight = Point(0,0,1)

    (1 to 12).foreach { hour =>
      val rotation = RotationY(hour * (PI / 6))
      val pos = rotation * midnight

      val x = pos.x * radius + center
      val z = height - (pos.z * radius + center)

      val c = hour / 12d
      canvas.writePixel(x.toInt, z.toInt, Color(0.2,c,1))
    }

    canvas.save
  }
}
