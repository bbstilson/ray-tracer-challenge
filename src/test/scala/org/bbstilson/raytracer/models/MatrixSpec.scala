package org.bbstilson.raytracer.models

import VectorToMatrix._

import org.bbstilson.raytracer.UnitSpec

class MatrixSpec extends UnitSpec {
  behavior of "Matrix"

  it should "multiply by another matrix" in {
    val m1 = Vector(
      Vector(1d,2d,3d,4d),
      Vector(5d,6d,7d,8d),
      Vector(9d,8d,7d,6d),
      Vector(5d,4d,3d,2d)
    )

    val m2 = Vector(
      Vector(-2d,1d,2d,3d),
      Vector(3d,2d,1d,-1d),
      Vector(4d,3d,6d,5d),
      Vector(1d,2d,7d,8d)
    )

    val m3 = Vector(
      Vector(20d,22d,50d,48d),
      Vector(44d,54d,114d,108d),
      Vector(40d,58d,110d,102d),
      Vector(16d,26d,46d,42d)
    )

    m1 * m2 should be(m3)
  }

  it should "multiply by a row" in {
    val m1 = Vector(
      Vector(1d,2d,3d,4d),
      Vector(2d,4d,4d,2d),
      Vector(8d,6d,4d,1d),
      Vector(0d,0d,0d,1d)
    )
    val r1 = Vector(1d,2d,3d,1d)
    val r2 = Vector(18d,24d,33d,1d)

    m1 * r1 should be(r2)
  }

  it should "multiply by an identity matrix" in {
    val m = Vector(
      Vector(0d,1d,2d,4d),
      Vector(1d,2d,4d,8d),
      Vector(2d,4d,8d,16d),
      Vector(4d,8d,16d,32d)
    )
    m * Matrix.identity(4) should be(m)
  }

  it should "multiply an identity matrix with a row" in {
    val r = Vector(1d,2d,3d,4d)
    Matrix.identity(4) * r should be(r)
  }

  it should "transpose" in {
    val m1 = Vector(
      Vector(0,9,3,0),
      Vector(9,8,0,8),
      Vector(1,8,5,3),
      Vector(0,0,5,8)
    )
    val m2 = Vector(
      Vector(0,9,1,0),
      Vector(9,8,8,0),
      Vector(3,0,5,5),
      Vector(0,8,3,8)
    )

    m1.transpose should be(m2)
  }

  it should "calculate the determinant of a 2x2 matrix" in {
    val m1 = Vector(
      Vector(1d,5d),
      Vector(-3d,2d)
    )
    m1.determinant should be(17)
  }

  it should "get a submatrix" in {
    val m1 = Vector(
      Vector(1d,5d,0d),
      Vector(-3d,2d,7d),
      Vector(0d,6d,-3d)
    )
    val m2 = Vector(
      Vector(-3d,2d),
      Vector(0d,6d)
    )

    m1.submatrix(0, 2) should be(m2)

    val m3 = Vector(
      Vector(-6d,1d,1d,6d),
      Vector(-8d,5d,8d,6d),
      Vector(-1d,0d,8d,2d),
      Vector(-7d,1d,-1d,1d)
    )

    val m4 = Vector(
      Vector(-6d,1d,6d),
      Vector(-8d,8d,6d),
      Vector(-7d,-1d,1d)
    )

    m3.submatrix(2, 1) should be(m4)
  }

  it should "calculate the minor of a 3x3 matrix" in {
    val m1 = Vector(
      Vector(3d,5d,0d),
      Vector(2d,-1d,-7d),
      Vector(6d,-1d,5d)
    )
    val m2 = m1.submatrix(1, 0)

    m2.determinant should be(m1.minor(1, 0))
  }

  it should "calculate a cofactor of a 3x3 matrix" in {
    val m = Vector(
      Vector(3d,5d,0d),
      Vector(2d,-1d,-7d),
      Vector(6d,-1d,5d)
    )

    m.minor(0, 0) should be(-12)
    m.cofactor(0, 0) should be(-12)
    m.minor(1, 0) should be(25)
    m.cofactor(1, 0) should be(-25)
  }

  it should "calculate the determinant of a 3x3 matrix" in {
    val m = Vector(
      Vector(1d,2d,6d),
      Vector(-5d,8d,-4d),
      Vector(2d,6d,4d)
    )

    m.cofactor(0,0) should be(56)
    m.cofactor(0,1) should be(12)
    m.cofactor(0,2) should be(-46)
    m.determinant should be(-196)
  }

  it should "calculate the determinant of a 4x4 matrix" in {
    val m = Vector(
      Vector(-2d,-8d, 3d, 5d),
      Vector(-3d, 1d, 7d, 3d),
      Vector( 1d, 2d,-9d, 6d),
      Vector(-6d, 7d, 7d,-9d)
    )

    m.cofactor(0, 0) should be(690)
    m.cofactor(0, 1) should be(447)
    m.cofactor(0, 2) should be(210)
    m.cofactor(0, 3) should be(51)
    m.determinant should be(-4071)
  }
}
