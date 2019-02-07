package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

import Math.{ PI, sqrt }

class RotationMatrixSpec extends UnitSpec {
  behavior of "RotationMatrix"

  it should "rotate a point around the x axis" in {
    val p = Point(0, 1, 0)
    val halfQuarter = RotationX(PI / 4)
    val fullQuarter = RotationX(PI / 2)
    halfQuarter * p should be(Point(0, sqrt(2)/2, sqrt(2)/2))
    fullQuarter * p should be(Point(0, 0, 1))
  }

  it should "rotate in the opposite direction for inverse" in {
    val p = Point(0, 1, 0)
    val halfQuarter = RotationX(PI / 4)
    val inv = halfQuarter.inverse
    inv * p should be(Point(0, sqrt(2)/2, -sqrt(2)/2))
  }

  it should "rotate a point around the y axis" in {
    val p = Point(0, 0, 1)
    val halfQuarter = RotationY(PI / 4)
    val fullQuarter = RotationY(PI / 2)
    halfQuarter * p should be(Point(sqrt(2)/2, 0, sqrt(2)/2))
    fullQuarter * p should be(Point(1, 0, 0))
  }

  it should "rotate a point around the z axis" in {
    val p = Point(0, 1, 0)
    val halfQuarter = RotationZ(PI / 4)
    val fullQuarter = RotationZ(PI / 2)
    halfQuarter * p should be(Point(-sqrt(2)/2, sqrt(2)/2, 0))
    fullQuarter * p should be(Point(-1, 0, 0))
  }
}
