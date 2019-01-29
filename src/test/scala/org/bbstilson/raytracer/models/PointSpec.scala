package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class PointSpec extends UnitSpec {
  behavior of "Points"

  it should "have accessible properties" in {
    val p = Point(4.3,-4.2,3.1)
    p.x should be(4.3)
    p.y should be(-4.2)
    p.z should be(3.1)
  }

  it should "add a vector" in {
    val p = Point(3,-2,5)
    val v = Vector(-2,3,1)
    p + v should be(Point(1,1,6))
  }

  it should "subtract a point" in {
    val p1 = Point(3,2,1)
    val p2 = Point(5,6,7)
    p1 - p2 should be(Vector(-2,-4,-6))
  }

  it should "subtract a vector" in {
    val p = Point(3,2,1)
    val v = Vector(5,6,7)
    p - v should be(Point(-2,-4,-6))
  }

  it should "be negatable" in {
    val p = Point(1,-2,3)
    -p should be(Point(-1,2,-3))
  }
}
