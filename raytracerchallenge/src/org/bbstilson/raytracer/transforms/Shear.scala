package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

case class Shear(m: Matrix) extends MatrixDouble(m)

object Shear {

  private def populate(xs: Map[(Int, Int), Double])(row: Int, col: Int, default: Double): Double = {
    xs.get((row, col)).getOrElse(default)
  }

  def apply(xy: Int, xz: Int, yx: Int, yz: Int, zx: Int, zy: Int): Shear = {
    val xs = Map(
      (0, 1) -> xy.toDouble,
      (0, 2) -> xz.toDouble,
      (1, 0) -> yx.toDouble,
      (1, 2) -> yz.toDouble,
      (2, 0) -> zx.toDouble,
      (2, 1) -> zy.toDouble
    )
    Shear(MatrixUtils.mkMatrix(populate(xs)))
  }
}
