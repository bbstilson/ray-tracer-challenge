package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.matrix._
import org.bbstilson.raytracer.models._

object ViewTransform {

  def apply(from: Point, to: Point, up: SceneVector): MatrixDouble = {
    val forward = (to - from).normalize
    val left = forward.cross(up.normalize)
    val trueUp = left.cross(forward)
    val orientation = new MatrixDouble(
      Vector(
        Vector(left.x, left.y, left.z, 0d),
        Vector(trueUp.x, trueUp.y, trueUp.z, 0d),
        Vector(-forward.x, -forward.y, -forward.z, 0d),
        Vector(0d, 0d, 0d, 1d)
      )
    )
    orientation * Translation(-from.x, -from.y, -from.z)
  }
}
