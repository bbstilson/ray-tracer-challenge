package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class VectorSpec extends UnitSpec {
  behavior of "Vectors"

  it should "have accessible properties" in {
    val v = Vector(6.7,-9.0,-100)
    v.x should be(6.7)
    v.y should be(-9.0)
    v.z should be(-100)
  }

  it should "add a vector" in {
    val v1 = Vector(3,-2,5)
    val v2 = Vector(-2,3,1)
    v1 + v2 should be(Vector(1,1,6))
  }

  it should "subtract a vector" in {
    val v1 = Vector(3,2,1)
    val v2 = Vector(5,6,7)
    v1 - v2 should be(Vector(-2,-4,-6))
  }

  it should "be negatable" in {
    val v = Vector(-1,2,-3)
    -v should be(Vector(1,-2,3))
  }

  it should "scale" in {
    val v = Vector(1,-2,3)
    v * 3.5 should be(Vector(3.5,-7,10.5))
    v * 0.5 should be(Vector(0.5,-1,1.5))
    v / 2 should be(Vector(0.5,-1,1.5))
  }

  it should "correctly compute magnitude" in {
    Vector(1,0,0).magnitude should be(1)
    Vector(0,1,0).magnitude should be(1)
    Vector(0,0,1).magnitude should be(1)
    Vector(1,2,3).magnitude should be(Math.sqrt(14))
    Vector(-1,-2,-3).magnitude should be(Math.sqrt(14))
  }

  it should "be normalizeable" in {
    val m = Math.sqrt(14)
    Vector(1,2,3).normalize should be(Vector(1/m,2/m,3/m))
    Vector(4,0,0).normalize should be(Vector(1,0,0))
    Vector(1,2,3).normalize.magnitude should be(1)
  }

  it should "compute the dot product of two vectors" in {
    val v1 = Vector(1,2,3)
    val v2 = Vector(2,3,4)
    v1 dot v2 should be(20)
  }

  it should "compute the cross product of two vectors" in {
    val v1 = Vector(1,2,3)
    val v2 = Vector(2,3,4)
    v1 cross v2 should be(Vector(-1,2,-1))
    v2 cross v1 should be(Vector(1,-2,1))
  }
}