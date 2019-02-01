package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class SceneVectorSpec extends UnitSpec {
  behavior of "SceneVectors"

  it should "have accessible properties" in {
    val v = SceneVector(6.7,-9.0,-100)
    v.x should be(6.7)
    v.y should be(-9.0)
    v.z should be(-100)
  }

  it should "add a SceneVector" in {
    val v1 = SceneVector(3,-2,5)
    val v2 = SceneVector(-2,3,1)
    v1 + v2 should be(SceneVector(1,1,6))
  }

  it should "subtract a SceneVector" in {
    val v1 = SceneVector(3,2,1)
    val v2 = SceneVector(5,6,7)
    v1 - v2 should be(SceneVector(-2,-4,-6))
  }

  it should "be negatable" in {
    val v = SceneVector(-1,2,-3)
    -v should be(SceneVector(1,-2,3))
  }

  it should "scale" in {
    val v = SceneVector(1,-2,3)
    v * 3.5 should be(SceneVector(3.5,-7,10.5))
    v * 0.5 should be(SceneVector(0.5,-1,1.5))
    v / 2 should be(SceneVector(0.5,-1,1.5))
  }

  it should "correctly compute magnitude" in {
    SceneVector(1,0,0).magnitude should be(1)
    SceneVector(0,1,0).magnitude should be(1)
    SceneVector(0,0,1).magnitude should be(1)
    SceneVector(1,2,3).magnitude should be(Math.sqrt(14))
    SceneVector(-1,-2,-3).magnitude should be(Math.sqrt(14))
  }

  it should "be normalizeable" in {
    val m = Math.sqrt(14)
    SceneVector(1,2,3).normalize should be(SceneVector(1/m,2/m,3/m))
    SceneVector(4,0,0).normalize should be(SceneVector(1,0,0))
    SceneVector(1,2,3).normalize.magnitude should be(1)
  }

  it should "compute the dot product of two SceneVectors" in {
    val v1 = SceneVector(1,2,3)
    val v2 = SceneVector(2,3,4)
    v1 dot v2 should be(20)
  }

  it should "compute the cross product of two SceneVectors" in {
    val v1 = SceneVector(1,2,3)
    val v2 = SceneVector(2,3,4)
    v1 cross v2 should be(SceneVector(-1,2,-1))
    v2 cross v1 should be(SceneVector(1,-2,1))
  }
}
