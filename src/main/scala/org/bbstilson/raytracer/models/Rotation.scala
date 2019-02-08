package org.bbstilson.raytracer.models

import MatrixDouble._
import org.bbstilson.raytracer.utils.MatrixUtils._

import Math.{ cos, sin }

class RotationX(m: Matrix) extends MatrixDouble(m)
class RotationY(m: Matrix) extends MatrixDouble(m)
class RotationZ(m: Matrix) extends MatrixDouble(m)

object RotationX {
  def apply(r: Double): RotationX = {
    val vals = Map(
      (0,0) -> 1d,
      (1,1) -> cos(r),
      (1,2) -> -sin(r),
      (2,1) -> sin(r),
      (2,2) -> cos(r),
      (3,3) -> 1d
    )
    val m = mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
    new RotationX(m)
  }
}

object RotationY {
  def apply(r: Double): RotationY = {
    val vals = Map(
      (0,0) -> cos(r),
      (0,2) -> sin(r),
      (1,1) -> 1d,
      (2,0) -> -sin(r),
      (2,2) -> cos(r),
      (3,3) -> 1d,
    )
    val m = mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
    new RotationY(m)
  }
}

object RotationZ {
  def apply(r: Double): RotationZ = {
    val vals = Map(
      (0,0) -> cos(r),
      (0,1) -> -sin(r),
      (1,0) -> sin(r),
      (1,1) -> cos(r),
      (2,2) -> 1d,
      (3,3) -> 1d
    )
    val m = mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
    new RotationZ(m)
  }
}
