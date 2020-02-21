package org.bbstilson.raytracer.utils

import org.bbstilson.raytracer.UnitSpec

class MatrixUtilsSpec extends UnitSpec {

  "MatrixUtils.mkMatrix" should "make a matrix" in {
    val expected = Vector(
      Vector(0, 1, 2, 3),
      Vector(1, 2, 3, 4),
      Vector(2, 3, 4, 5),
      Vector(3, 4, 5, 6)
    )

    MatrixUtils.mkMatrix(4, 4, (r, c) => r + c)
  }

  "MatrixUtils.identity" should "return an identity matrix" in {
     val expected = Vector(
       Vector(1d,0d,0d,0d),
       Vector(0d,1d,0d,0d),
       Vector(0d,0d,1d,0d),
       Vector(0d,0d,0d,1d)
     )

     MatrixUtils.identity should be(expected)
  }
}
