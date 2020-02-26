package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.UnitSpec

class ScaleSpec extends UnitSpec {

  it should "scale a point" in {
    val transform = Scale(2, 3, 4)
    val p = Point(-4, 6, 8)
    transform * p should be(Point(-8, 18, 32))
  }

  it should "scale a scene vector" in {
    val transform = Scale(2, 3, 4)
    val v = Vector3(-4, 6, 8)
    transform * v should be(Vector3(-8, 18, 32))
  }

  it should "revert via its inverse" in {
    val transform = Scale(2, 3, 4)
    val inv = transform.inverse
    val v = Vector3(-4, 6, 8)
    inv * v should be(Vector3(-2, 2, 2))
  }

  it should "reflect" in {
    val transform = Scale(-1, 1, 1)
    val p = Point(2, 3, 4)
    transform * p should be(Point(-2, 3, 4))
  }
}
