package org.bbstilson.raytracer.utils

import org.bbstilson.raytracer.UnitSpec

class PositiveDoubleSpec extends UnitSpec {
  behavior of "PositiveDouble"

  it should "reject negative values" in {
    an [IllegalArgumentException] should be thrownBy(PositiveDouble(-1))
  }

  it should "allow positive values" in {
    PositiveDouble(1).n should be(1)
  }
}
