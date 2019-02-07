package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

import Math.{ PI }

class TransformationSpec extends UnitSpec {
  behavior of "Transformation"

  "Individual transformations" should "applied in sequence" in {
    val p = Point(1, 0, 1)
    val a = RotationX(PI / 2)
    val b = ScaleMatrix(5, 5, 5)
    val c = TranslationMatrix(10, 5, 7)

    // apply rotation first
    val p2 = a * p
    p2 should be(Point(1, -1, 0))

    // then apply scaling
    val p3 = b * p2
    p3 should be(Point(5, -5, 0))

    // then apply translation
    val p4 = c * p3
    p4 should be(Point(15, 0, 7))
  }

  "Chained transformations" should "be applied in reverse order" in {
    val p = Point(1, 0, 1)
    val a = RotationX(PI / 2)
    val b = ScaleMatrix(5, 5, 5)
    val c = TranslationMatrix(10, 5, 7)
    val t = c * b * a

    t * p should be(Point(15, 0, 7))
  }
}
