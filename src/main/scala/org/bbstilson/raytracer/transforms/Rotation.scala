package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.matrix._
import MatrixUtils._
import MatrixDouble._

import Math.{cos, sin}

case class RotationX(m: Matrix) extends MatrixDouble(m)
case class RotationY(m: Matrix) extends MatrixDouble(m)
case class RotationZ(m: Matrix) extends MatrixDouble(m)

object Rotation {

  def populate(
    xs: Map[(Int, Int), Double]
  )(row: Int, col: Int, default: Double): Double = {
    xs.get((row, col)).getOrElse(default)
  }
}

object RotationX {

  def apply(r: Double): RotationX = {
    val xs = Map(
      (1, 1) -> cos(r),
      (1, 2) -> -sin(r),
      (2, 1) -> sin(r),
      (2, 2) -> cos(r)
    )
    RotationX(MatrixUtils.mkMatrix(Rotation.populate(xs)))
  }
}

object RotationY {

  def apply(r: Double): RotationY = {
    val xs = Map(
      (0, 0) -> cos(r),
      (0, 2) -> sin(r),
      (2, 0) -> -sin(r),
      (2, 2) -> cos(r)
    )
    RotationY(MatrixUtils.mkMatrix(Rotation.populate(xs)))
  }
}

object RotationZ {

  def apply(r: Double): RotationZ = {
    val xs = Map(
      (0, 0) -> cos(r),
      (0, 1) -> -sin(r),
      (1, 0) -> sin(r),
      (1, 1) -> cos(r)
    )
    RotationZ(MatrixUtils.mkMatrix(Rotation.populate(xs)))
  }
}
