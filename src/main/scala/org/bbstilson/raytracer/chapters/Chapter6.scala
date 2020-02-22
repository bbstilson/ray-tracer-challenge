package org.bbstilson.raytracer.chapters

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.transforms._
import org.bbstilson.raytracer.shapes._
import org.bbstilson.raytracer.view._

import Math.PI

object Chapter6 {

  def main(args: Array[String]): Unit = {
    // Lighting
    val lightPosition = Point(-10, 10, -10)
    val lightColor = Color(1, 1, 1)
    val light = PointLight(lightPosition, lightColor)

    // Sphere settings
    val sphere = Sphere(
      RotationZ(PI / 4) * Scale(0.5, 1, 1),
      Material(Color(0.2, 0.5, 0.7))
    )

    val rayOrigin = Point(0, 0, -5)
    val wallZ = 10d
    val wallSize = 7d
    val canvasPixels = 400
    val pixelSize = wallSize / canvasPixels
    val half = wallSize / 2

    val canvas = new Canvas(canvasPixels, canvasPixels)
    val red = Color(1, 0, 0)

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
          case Some(hit) => {
            val point = ray.position(hit.t)
            val normal = hit.o.normalAt(point)
            val eye = -ray.direction
            val color = hit.o.material.light(light, point, eye, normal)
            canvas.writePixel(x, y, color)
          }
          case _ => // Miss. Do nothing
        }
      }
    }

    canvas.save
  }
}
