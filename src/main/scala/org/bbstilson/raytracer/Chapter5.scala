package org.bbstilson.raytracer

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.transforms._

import Math.PI

object Chapter5 {
  def main(args: Array[String]): Unit = {

    // This is verrrry slow. (~1 min)
    (0 to 4).foreach { opt =>
      val sphere = mkFunSphere(opt)
      val rayOrigin = Point(0, 0, -5)
      val wallZ = 10d
      val wallSize = 7d
      val canvasPixels = 400
      val pixelSize = wallSize / canvasPixels
      val half = wallSize / 2

      val canvas = new Canvas(canvasPixels, canvasPixels)
      val red = Color(1,0,0)

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

  private def mkFunSphere(opt: Int): Sphere = {
    opt match {
      // borin sphere
      case 0 => Sphere()
      // shrink it along the y axis
      case 1 => Sphere(Scale(1, 0.5, 1))
      // shrink it along the x-axis
      case 2 => Sphere(Scale(0.5, 1, 1))
      // shrink it, and rotate it!
      case 3 => Sphere(RotationZ(PI / 4) * Scale(0.5, 1, 1))
      // shrink it, and skew it!
      case 4 => Sphere(Shear(1, 0, 0, 0, 0, 0) * Scale(0.5, 1, 1))
    }
  }
}
