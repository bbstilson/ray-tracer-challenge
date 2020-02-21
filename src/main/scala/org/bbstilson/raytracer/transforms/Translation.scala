package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

case class Translation(m: Matrix) extends MatrixDouble(m) {
  override def *(sv: SceneVector): SceneVector = sv
}

object Translation {
  def apply(x: Int, y: Int, z: Int): Translation = {
    val vals = Vector(x.toDouble, y.toDouble, z.toDouble, 1d)
    val f = (r: Int, c: Int) => if (r == c) 1d else if (c == 3) vals(r) else 0d
    val m = mkMatrix(4, 4, f)
    new Translation(m)
  }
}
