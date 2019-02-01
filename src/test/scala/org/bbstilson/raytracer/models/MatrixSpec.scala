package org.bbstilson.raytracer.models

import VectorToMatrix._

import org.bbstilson.raytracer.UnitSpec

class MatrixSpec extends UnitSpec {
  behavior of "Matrix"

  it should "multiply" in {
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
}
