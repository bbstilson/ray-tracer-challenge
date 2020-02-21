package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

class Shear(m: Matrix) extends MatrixDouble(m)

object Shear {
  def apply(xy: Int, xz: Int, yx: Int, yz: Int, zx: Int, zy: Int): Shear = {
    val vals = Map(
      (0,0) -> 1d,
      (1,1) -> 1d,
      (2,2) -> 1d,
      (3,3) -> 1d,
      (0,1) -> xy.toDouble,
      (0,2) -> xz.toDouble,
      (1,0) -> yx.toDouble,
      (1,2) -> yz.toDouble,
      (2,0) -> zx.toDouble,
      (2,1) -> zy.toDouble
    )
    val m = mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
    new Shear(m)
  }
}
