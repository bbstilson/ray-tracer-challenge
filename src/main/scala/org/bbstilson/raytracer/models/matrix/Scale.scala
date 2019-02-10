package org.bbstilson.raytracer.models.matrix

import org.bbstilson.raytracer.utils.MatrixUtils._
import MatrixDouble._

class Scale(m: Matrix) extends MatrixDouble(m)

object Scale {
  def apply(x: Double, y: Double, z: Double): Scale = {
    val vals = Vector(x, y, z, 1)
    val m = mkMatrix(4, 4, (r, c) => if (r == c) vals(r) else 0d)
    new Scale(m)
  }
}
