package org.bbstilson.raytracer.models

import MatrixDouble._

import Math.{ cos, sin }

trait RotationMatrix {
  val tMatrix: MatrixDouble

  def *(p: Point): Point = tMatrix * p
  def *(sv: SceneVector): SceneVector = tMatrix * sv

  def inverse: MatrixDouble = tMatrix.inverse
}

case class RotationX(r: Double) extends RotationMatrix {
  val tMatrix = {
    val vals = Map(
      (0,0) -> 1d,
      (1,1) -> cos(r),
      (1,2) -> -sin(r),
      (2,1) -> sin(r),
      (2,2) -> cos(r),
      (3,3) -> 1d
    )
    mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
  }
}

case class RotationY(r: Double) extends RotationMatrix {
  val tMatrix = {
    val vals = Map(
      (0,0) -> cos(r),
      (0,2) -> sin(r),
      (1,1) -> 1d,
      (2,0) -> -sin(r),
      (2,2) -> cos(r),
      (3,3) -> 1d,
    )
    mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
  }
}

case class RotationZ(r: Double) extends RotationMatrix {
  val tMatrix = {
    val vals = Map(
      (0,0) -> cos(r),
      (0,1) -> -sin(r),
      (1,0) -> sin(r),
      (1,1) -> cos(r),
      (2,2) -> 1d,
      (3,3) -> 1d
    )
    mkMatrix(4, 4, (r, c) => vals.getOrElse((r, c), 0d))
  }
}
