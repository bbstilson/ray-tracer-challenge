package org.bbstilson.raytracer.models

import MatrixDouble._

case class TranslationMatrix(x: Int, y: Int, z: Int) {
  private val tMatrix = {
    val vals = Vector(x, y, z, 1)
    mkMatrix(4, 4, (r, c) => if (r == c) 1d else if (c == 3) vals(r) else 0d)
  }

  def *(p: Point): Point = tMatrix * p
  def *(sv: SceneVector): SceneVector = sv

  def inverse: MatrixDouble = tMatrix.inverse
}
