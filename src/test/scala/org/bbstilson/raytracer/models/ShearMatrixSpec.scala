package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

import Math.{ PI, sqrt }

class ShearMatrixSpec extends UnitSpec {
  behavior of "ShearMatrix"

  it should "move x in proportion to y " in {
    val transform = ShearMatrix(1, 0, 0, 0, 0, 0)
    val p = Point(2, 3, 4)
    transform * p should be(Point(5, 3, 4))
  }

  it should "moves x in proportion to z" in {
    val transform = ShearMatrix(0, 1, 0, 0, 0, 0)
    val p = Point(2, 3, 4)
    transform * p should be(Point(6, 3, 4))
  }

  it should "move y in proportion to x" in {
    val transform = ShearMatrix(0, 0, 1, 0, 0, 0)
    val p = Point(2, 3, 4)
    transform * p should be(Point(2, 5, 4))
  }

  it should "move y in proportion to z" in {
    val transform = ShearMatrix(0, 0, 0, 1, 0, 0)
    val p = Point(2, 3, 4)
    transform * p should be(Point(2, 7, 4))
  }

  it should "move z in proportion to x" in {
    val transform = ShearMatrix(0, 0, 0, 0, 1, 0)
    val p = Point(2, 3, 4)
    transform * p should be(Point(2, 3, 6))
  }

  it should "move z in proportion to y" in {
    val transform = ShearMatrix(0, 0, 0, 0, 0, 1)
    val p = Point(2, 3, 4)
    transform * p should be(Point(2, 3, 7))
  }
}
