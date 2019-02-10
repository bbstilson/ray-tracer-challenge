package org.bbstilson.raytracer.models.matrix

import org.bbstilson.raytracer.utils.MatrixUtils._
import org.bbstilson.raytracer.models.SceneVector
import MatrixDouble._

case class Translation(m: Matrix) extends MatrixDouble(m) {
  override def *(sv: SceneVector): SceneVector = sv
}

object Translation {
  def apply(x: Int, y: Int, z: Int): Translation = {
    val vals = Vector(x, y, z, 1)
    val f = (r: Int, c: Int) => if (r == c) 1d else if (c == 3) vals(r) else 0d
    val m = mkMatrix(4, 4, f)
    new Translation(m)
  }
}
