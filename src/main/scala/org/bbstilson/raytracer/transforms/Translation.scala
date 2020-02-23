package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

final case class Translation(m: Matrix) extends MatrixDouble(m) {
  override def *(sv: SceneVector): SceneVector = sv
}

object Translation {

  def populate(xs: Vector[Double])(row: Int, col: Int, default: Double): Double = {
    if (col == 3) xs(row) else default
  }

  def apply(x: Double, y: Double, z: Double): Translation = {
    val xs = Vector(x, y, z, 1)
    Translation(MatrixUtils.mkMatrix(populate(xs)))
  }
}
