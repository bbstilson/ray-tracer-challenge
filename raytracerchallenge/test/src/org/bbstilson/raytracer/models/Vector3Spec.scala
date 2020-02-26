package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

import Math.{sqrt}

class Vector3Spec extends UnitSpec {

  it should "have accessible properties" in {
    val v = Vector3(6.7, -9.0, -100)
    v.x should be(6.7)
    v.y should be(-9.0)
    v.z should be(-100)
  }

  it should "add a Vector3" in {
    val v1 = Vector3(3, -2, 5)
    val v2 = Vector3(-2, 3, 1)
    v1 + v2 should be(Vector3(1, 1, 6))
  }

  it should "subtract a Vector3" in {
    val v1 = Vector3(3, 2, 1)
    val v2 = Vector3(5, 6, 7)
    v1 - v2 should be(Vector3(-2, -4, -6))
  }

  it should "be negatable" in {
    val v = Vector3(-1, 2, -3)
    -v should be(Vector3(1, -2, 3))
  }

  it should "scale" in {
    val v = Vector3(1, -2, 3)
    v * 3.5 should be(Vector3(3.5, -7, 10.5))
    v * 0.5 should be(Vector3(0.5, -1, 1.5))
    v / 2 should be(Vector3(0.5, -1, 1.5))
  }

  it should "correctly compute magnitude" in {
    Vector3(1, 0, 0).magnitude should be(1)
    Vector3(0, 1, 0).magnitude should be(1)
    Vector3(0, 0, 1).magnitude should be(1)
    Vector3(1, 2, 3).magnitude should be(Math.sqrt(14))
    Vector3(-1, -2, -3).magnitude should be(Math.sqrt(14))
  }

  it should "be normalizeable" in {
    val m = Math.sqrt(14)
    Vector3(1, 2, 3).normalize should be(Vector3(1 / m, 2 / m, 3 / m))
    Vector3(4, 0, 0).normalize should be(Vector3(1, 0, 0))
    Vector3(1, 2, 3).normalize.magnitude should be(1)
  }

  it should "compute the dot product of two Vector3s" in {
    val v1 = Vector3(1, 2, 3)
    val v2 = Vector3(2, 3, 4)
    v1.dot(v2) should be(20)
  }

  it should "compute the cross product of two Vector3s" in {
    val v1 = Vector3(1, 2, 3)
    val v2 = Vector3(2, 3, 4)
    v1.cross(v2) should be(Vector3(-1, 2, -1))
    v2.cross(v1) should be(Vector3(1, -2, 1))
  }

  it should "reflect approaching at 45°" in {
    val v = Vector3(1, -1, 0)
    val n = Vector3(0, 1, 0)
    val r = Vector3.reflect(v, n)
    r should be(Vector3(1, 1, 0))
  }

  it should "reflect off a slanted surface" in {
    val v = Vector3(0, -1, 0)
    val n = Vector3(sqrt(2) / 2, sqrt(2) / 2, 0)
    val r = Vector3.reflect(v, n)
    r should be(Vector3(1, 0, 0))
  }
}
