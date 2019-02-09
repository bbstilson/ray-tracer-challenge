package org.bbstilson.raytracer

import org.bbstilson.raytracer.models._

import Math.PI

object Chapter5 {
  def main(args: Array[String]): Unit = {
    val rayOrigin = Point(0, 0, -5)
    val wallZ = 10d
    val wallSize = 7d
    val canvasPixels = 100
    val pixelSize = wallSize / canvasPixels
    val half = wallSize / 2

    val canvas = new Canvas(100, 100)
    val red = Color(1,0,0)

    val sphere = Sphere()

    (0 until canvasPixels).foreach { y =>
      (0 until canvasPixels).foreach { x =>
        // compute the world x coordinate (left = -half, right = half)
        val worldX = -half + pixelSize * x
        // compute the world y coordinate (top = +half, bottom = -half)
        val worldY = half - pixelSize * y
        // describe the point on the wall that the ray will target
        val position = Point(worldX, worldY, wallZ)
        val substractedPosition = position - rayOrigin
        val normalizedPosition = substractedPosition.normalize
        val ray = Ray(rayOrigin, normalizedPosition)

        Intersection.hit(sphere.intersect(ray)) match {
          case Some(h) => {
            canvas.writePixel(x, y, red)
          }
          case _ => // Miss. Do nothing
        }
      }
    }

    canvas.save
  }
}
