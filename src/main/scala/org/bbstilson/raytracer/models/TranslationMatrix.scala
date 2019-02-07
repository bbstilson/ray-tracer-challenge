package org.bbstilson.raytracer.models

import MatrixDouble._

case class TranslationMatrix(m: Matrix) extends MatrixDouble(m)

object TranslationMatrix {
  def apply(x: Int, y: Int, z: Int): TranslationMatrix = {
    val vals = Vector(x, y, z, 1)
    val f = (r: Int, c: Int) => if (r == c) 1d else if (c == 3) vals(r) else 0d
    val m = mkMatrix(4, 4, f)
    new TranslationMatrix(m)
  }
}
