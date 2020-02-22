package org.bbstilson.raytracer.view

import org.bbstilson.raytracer.matrix.MatrixUtils._
import org.bbstilson.raytracer.models.Ray
import org.bbstilson.raytracer.matrix.MatrixDouble
import org.bbstilson.raytracer.models.Point

import scala.collection.parallel.CollectionConverters._

final case class Camera(
  hSize: Int,
  vSize: Int,
  fieldOfView: Double,
  transform: MatrixDouble = new MatrixDouble(identity)
) {

  val halfView = Math.tan(fieldOfView / 2)
  val aspect = hSize.toDouble / vSize.toDouble

  val (halfWidth, halfHeight) = if (aspect >= 1) {
    (halfView, halfView / aspect)
  } else {
    (halfView * aspect, halfView)
  }
  val pixelSize = (halfWidth * 2) / hSize.toDouble

  def rayForPixel(x: Int, y: Int): Ray = {
    // The offset from the edge of the canvas to the pixel's center.
    val xOffset = (x + 0.5) * pixelSize
    val yOffset = (y + 0.5) * pixelSize
    // The untransformed coordinates of the pixel in world space.
    // (Remember that the camera looks toward -z, so +x is to the *left*.)
    val worldX = halfWidth - xOffset
    val worldY = halfHeight - yOffset
    // Using the camera matrix, transform the canvas point and the origin,
    // and then compute the ray's direction vector.
    // (remember that the canvas is at z=-1x)
    val tInverse = transform.inverse
    val pixel = tInverse * Point(worldX, worldY, -1)
    val origin = tInverse * Point(0, 0, 0)
    val direction = (pixel - origin).normalize

    Ray(origin, direction)
  }

  def render(w: World): Canvas = {
    val canvas = Canvas(hSize, vSize)

    val pixels = for {
      y <- 0 to (vSize - 1)
      x <- 0 to (hSize - 1)
    } yield (x, y)

    pixels.par.foreach {
      case (x, y) =>
        canvas.writePixel(x, y, w.colorAt(rayForPixel(x, y)))
    }
    canvas
  }
}
