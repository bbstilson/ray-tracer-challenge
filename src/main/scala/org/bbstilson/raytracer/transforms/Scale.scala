package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

class Scale(m: Matrix) extends MatrixDouble(m)

object Scale {

  def apply(x: Double, y: Double, z: Double): Scale = {
    val vals = Vector(x, y, z, 1)
    val m = mkMatrix(4, 4, (r, c) => if (r == c) vals(r) else 0d)
    new Scale(m)
  }
}
