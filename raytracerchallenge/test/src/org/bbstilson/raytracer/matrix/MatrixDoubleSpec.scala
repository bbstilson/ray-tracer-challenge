package org.bbstilson.raytracer.matrix

import org.bbstilson.raytracer.UnitSpec

class MatrixDoubleSpec extends UnitSpec {

  it should "multiply by another matrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(1d, 2d, 3d, 4d),
        Vector(5d, 6d, 7d, 8d),
        Vector(9d, 8d, 7d, 6d),
        Vector(5d, 4d, 3d, 2d)
      )
    )

    val b = new MatrixDouble(
      Vector(
        Vector(-2d, 1d, 2d, 3d),
        Vector(3d, 2d, 1d, -1d),
        Vector(4d, 3d, 6d, 5d),
        Vector(1d, 2d, 7d, 8d)
      )
    )

    val c = new MatrixDouble(
      Vector(
        Vector(20d, 22d, 50d, 48d),
        Vector(44d, 54d, 114d, 108d),
        Vector(40d, 58d, 110d, 102d),
        Vector(16d, 26d, 46d, 42d)
      )
    )

    a * b should be(c)
  }

  it should "multiply by a row" in {
    val a = new MatrixDouble(
      Vector(
        Vector(1d, 2d, 3d, 4d),
        Vector(2d, 4d, 4d, 2d),
        Vector(8d, 6d, 4d, 1d),
        Vector(0d, 0d, 0d, 1d)
      )
    )
    val r1 = Vector(1d, 2d, 3d, 1d)
    val r2 = Vector(18d, 24d, 33d, 1d)

    a * r1 should be(r2)
  }

  it should "multiply by an identity matrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(0d, 1d, 2d, 4d),
        Vector(1d, 2d, 4d, 8d),
        Vector(2d, 4d, 8d, 16d),
        Vector(4d, 8d, 16d, 32d)
      )
    )
    val identity = new MatrixDouble(MatrixUtils.getIdentityMatrix)
    a * identity should be(a)
  }

  it should "multiply an identity matrix with a row" in {
    val r = Vector(1d, 2d, 3d, 4d)
    val identity = new MatrixDouble(MatrixUtils.getIdentityMatrix)
    identity * r should be(r)
  }

  it should "transpose" in {
    val a = new MatrixDouble(
      Vector(
        Vector(0, 9, 3, 0),
        Vector(9, 8, 0, 8),
        Vector(1, 8, 5, 3),
        Vector(0, 0, 5, 8)
      )
    )
    val b = new MatrixDouble(
      Vector(
        Vector(0, 9, 1, 0),
        Vector(9, 8, 8, 0),
        Vector(3, 0, 5, 5),
        Vector(0, 8, 3, 8)
      )
    )

    a.transpose should be(b)
  }

  it should "calculate the determinant of a 2x2 matrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(1d, 5d),
        Vector(-3d, 2d)
      )
    )
    a.determinant should be(17)
  }

  it should "get a submatrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(1d, 5d, 0d),
        Vector(-3d, 2d, 7d),
        Vector(0d, 6d, -3d)
      )
    )
    val b = new MatrixDouble(
      Vector(
        Vector(-3d, 2d),
        Vector(0d, 6d)
      )
    )

    a.submatrix(0, 2) should be(b)

    val c = new MatrixDouble(
      Vector(
        Vector(-6d, 1d, 1d, 6d),
        Vector(-8d, 5d, 8d, 6d),
        Vector(-1d, 0d, 8d, 2d),
        Vector(-7d, 1d, -1d, 1d)
      )
    )

    val d = new MatrixDouble(
      Vector(
        Vector(-6d, 1d, 6d),
        Vector(-8d, 8d, 6d),
        Vector(-7d, -1d, 1d)
      )
    )

    c.submatrix(2, 1) should be(d)
  }

  it should "calculate the minor of a 3x3 matrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(3d, 5d, 0d),
        Vector(2d, -1d, -7d),
        Vector(6d, -1d, 5d)
      )
    )
    val b = a.submatrix(1, 0)

    b.determinant should be(a.minor(1, 0))
  }

  it should "calculate a cofactor of a 3x3 matrix" in {
    val m = new MatrixDouble(
      Vector(
        Vector(3d, 5d, 0d),
        Vector(2d, -1d, -7d),
        Vector(6d, -1d, 5d)
      )
    )

    m.minor(0, 0) should be(-12)
    m.cofactor(0, 0) should be(-12)
    m.minor(1, 0) should be(25)
    m.cofactor(1, 0) should be(-25)
  }

  it should "calculate the determinant of a 3x3 matrix" in {
    val m = new MatrixDouble(
      Vector(
        Vector(1d, 2d, 6d),
        Vector(-5d, 8d, -4d),
        Vector(2d, 6d, 4d)
      )
    )

    m.cofactor(0, 0) should be(56)
    m.cofactor(0, 1) should be(12)
    m.cofactor(0, 2) should be(-46)
    m.determinant should be(-196)
  }

  it should "calculate the determinant of a 4x4 matrix" in {
    val m = new MatrixDouble(
      Vector(
        Vector(-2d, -8d, 3d, 5d),
        Vector(-3d, 1d, 7d, 3d),
        Vector(1d, 2d, -9d, 6d),
        Vector(-6d, 7d, 7d, -9d)
      )
    )

    m.cofactor(0, 0) should be(690)
    m.cofactor(0, 1) should be(447)
    m.cofactor(0, 2) should be(210)
    m.cofactor(0, 3) should be(51)
    m.determinant should be(-4071)
  }

  it should "check for invertibility" in {
    val a = new MatrixDouble(
      Vector(
        Vector(6d, 4d, 4d, 4d),
        Vector(5d, 5d, 7d, 6d),
        Vector(4d, -9d, 3d, -7d),
        Vector(9d, 1d, 7d, -6d)
      )
    )

    a.determinant should be(-2120)
    a.isInvertible should be(true)

    val b = new MatrixDouble(
      Vector(
        Vector(-4d, 2d, -2d, -3d),
        Vector(9d, 6d, 2d, 6d),
        Vector(0d, -5d, 1d, -5d),
        Vector(0d, 0d, 0d, 0d)
      )
    )

    b.determinant should be(0)
    b.isInvertible should be(false)
  }

  it should "invert a matrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(-5d, 2d, 6d, -8d),
        Vector(1d, -5d, 1d, 8d),
        Vector(7d, 7d, -6d, -7d),
        Vector(1d, -3d, 7d, 4d)
      )
    )

    val b = a.inverse

    a.determinant should be(532)
    a.cofactor(2, 3) should be(-160)
    b(3, 2) should be(-160d / 532)
    a.cofactor(3, 2) should be(105)
    b(2, 3) should be(105d / 532)

    val expectedInverse = new MatrixDouble(
      Vector(
        Vector(0.21805, 0.45113, 0.24060, -0.04511),
        Vector(-0.80827, -1.45677, -0.44361, 0.52068),
        Vector(-0.07895, -0.22368, -0.05263, 0.19737),
        Vector(-0.52256, -0.81391, -0.30075, 0.30639)
      )
    )

    (b ~= expectedInverse) should be(true)
  }

  it should "calculate the inverse of another matrix" in {
    val a = new MatrixDouble(
      Vector(
        Vector(8d, -5d, 9d, 2d),
        Vector(7d, 5d, 6d, 1d),
        Vector(-6d, 0d, 9d, 6d),
        Vector(-3d, 0d, -9d, -4d)
      )
    )

    val expectedInverse = new MatrixDouble(
      Vector(
        Vector(-0.15385, -0.15385, -0.28205, -0.53846),
        Vector(-0.07692, 0.12308, 0.02564, 0.03077),
        Vector(0.35897, 0.35897, 0.43590, 0.92308),
        Vector(-0.69231, -0.69231, -0.76923, -1.92308)
      )
    )

    (a.inverse ~= expectedInverse) should be(true)

    val b = new MatrixDouble(
      Vector(
        Vector(9d, 3d, 0d, 9d),
        Vector(-5d, -2d, -6d, -3d),
        Vector(-4d, 9d, 6d, 4d),
        Vector(-7d, 6d, 6d, 2d)
      )
    )

    val expectedInverse2 = new MatrixDouble(
      Vector(
        Vector(-0.04074, -0.07778, 0.14444, -0.22222),
        Vector(-0.07778, 0.03333, 0.36667, -0.33333),
        Vector(-0.02901, -0.14630, -0.10926, 0.12963),
        Vector(0.17778, 0.06667, -0.26667, 0.33333)
      )
    )

    (b.inverse ~= expectedInverse2) should be(true)
  }

  it should "multiply and invert" in {
    val a = new MatrixDouble(
      Vector(
        Vector(3d, -9d, 7d, 3d),
        Vector(3d, -8d, 2d, -9d),
        Vector(-4d, 4d, 4d, 1d),
        Vector(-6d, 5d, -1d, 1d)
      )
    )
    val b = new MatrixDouble(
      Vector(
        Vector(8d, 2d, 2d, 2d),
        Vector(3d, -1d, 7d, 0d),
        Vector(7d, 0d, 5d, 4d),
        Vector(6d, -2d, 0d, 5d)
      )
    )

    val c = a * b

    ((c * b.inverse) ~= a) should be(true)
  }
}
