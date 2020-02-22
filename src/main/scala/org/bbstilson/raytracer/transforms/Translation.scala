package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

case class Translation(m: Matrix) extends MatrixDouble(m) {
  override def *(sv: SceneVector): SceneVector = sv
}

object Translation {

  def apply(x: Double, y: Double, z: Double): Translation = {
    val vals = Vector(x, y, z, 1)
    val f = (r: Int, c: Int) => if (r == c) 1d else if (c == 3) vals(r) else 0d
    val m = mkMatrix(4, 4, f)
    new Translation(m)
  }
}
