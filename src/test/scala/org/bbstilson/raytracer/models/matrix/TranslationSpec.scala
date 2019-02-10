package org.bbstilson.raytracer.models.matrix

import org.bbstilson.raytracer.UnitSpec
import org.bbstilson.raytracer.models._

class TranslationSpec extends UnitSpec {
  behavior of "Translation"

  it should "multiply by a point" in {
    val transform = Translation(5, -3, 2)
    val p = Point(-3, 4, 5)
    transform * p should be(Point(2, 1, 7))
  }

  it should "multiply the inverse by a point" in {
    val transform = Translation(5, -3, 2)
    val inv = transform.inverse
    val p = Point(-3, 4, 5)
    inv * p should be(Point(-8, 7, 3))
  }

  it should "not affect scene vectors" in {
    val transform = Translation(5, -3, 2)
    val v = SceneVector(-3, 4, 5)
    transform * v should be(v)
  }
}
