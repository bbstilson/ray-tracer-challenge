package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec
import org.bbstilson.raytracer.models.matrix._

class RaySpec extends UnitSpec {
  behavior of "Ray"

  it should "be query-able" in {
    val origin = Point(1, 2, 3)
    val direction = SceneVector(4, 5, 6)
    val r = Ray(origin, direction)
    r.origin should be(origin)
    r.direction should be(direction)
  }

  it should "compute a point from a distance" in {
    val r = Ray(Point(2, 3, 4), SceneVector(1, 0, 0))
    r.position(0d) should be(Point(2, 3, 4))
    r.position(1d) should be(Point(3, 3, 4))
    r.position(-1d) should be(Point(1, 3, 4))
    r.position(2.5) should be(Point(4.5, 3, 4))
  }

  it should "translate" in {
    val r = Ray(Point(1, 2, 3), SceneVector(0, 1, 0))
    val m = Translation(3, 4, 5)
    val r2 = r.transform(m)
    r2.origin should be(Point(4, 6, 8))
    r2.direction should be(SceneVector(0, 1, 0))
  }

  it should "scale" in {
    val r = Ray(Point(1, 2, 3), SceneVector(0, 1, 0))
    val m = Scale(2, 3, 4)
    val r2 = r.transform(m)
    r2.origin should be(Point(2, 6, 12))
    r2.direction should be(SceneVector(0, 3, 0))
  }
}
