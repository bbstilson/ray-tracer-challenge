package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

final case class Scale(m: Matrix) extends MatrixDouble(m)

object Scale {

  private def populate(xs: Vector[Double])(row: Int, col: Int, default: Double): Double = {
    if (row == col) xs(row) else default
  }

  def apply(x: Double, y: Double, z: Double): Scale = {
    val xs = Vector(x, y, z, 1)
    Scale(MatrixUtils.mkMatrix(populate(xs)))
  }
}
