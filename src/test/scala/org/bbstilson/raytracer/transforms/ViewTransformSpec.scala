package org.bbstilson.raytracer.transforms

import org.bbstilson.raytracer.matrix._
import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.transforms._
import org.bbstilson.raytracer.UnitSpec

class ViewTransformSpec extends UnitSpec {
  it should "handle default orientation" in {
    val t = ViewTransform(Point(0, 0, 0), Point(0, 0, -1), Vector3(0, 1, 0))
    t shouldBe new MatrixDouble(MatrixUtils.getIdentityMatrix)
  }

  it should "handle positive z direction" in {
    val t = ViewTransform(Point(0, 0, 0), Point(0, 0, 1), Vector3(0, 1, 0))
    t shouldBe Scale(-1, 1, -1)
  }

  it should "move the world" in {
    val t = ViewTransform(Point(0, 0, 8), Point(0, 0, 0), Vector3(0, 1, 0))
    t shouldBe Translation(0, 0, -8)
  }

  it should "move to an arbitrary position" in {
    val t = ViewTransform(Point(1, 3, 2), Point(4, -2, 8), Vector3(1, 1, 0))
    t shouldBe new MatrixDouble(
      Vector(
        Vector(-0.50709, 0.50709, 0.67612, -2.36643),
        Vector(0.76772, 0.60609, 0.12122, -2.82843),
        Vector(-0.35857, 0.59761, -0.71714, 0.00000),
        Vector(0.00000, 0.00000, 0.00000, 1.00000)
      )
    )
  }
}
