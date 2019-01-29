package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class ColorSpec extends UnitSpec {
  behavior of "Colors"

  it should "have accessible properties" in {
    val c = Color(-0.5,0.4,1.7)
    c.r should be(-0.5)
    c.g should be(0.4)
    c.b should be(1.7)
  }

  it should "add a color" in {
    val c1 = Color(0.9,0.6,0.75)
    val c2 = Color(0.7,0.1,0.25)
    c1 + c2 should be(Color(1.6,0.7,1.0))
  }

  it should "subtract a color" in {
    val c1 = Color(0.9,0.6,0.75)
    val c2 = Color(0.7,0.1,0.25)
    c1 - c2 should be(Color(0.2,0.5,0.5))
  }

  it should "scale" in {
    val c = Color(0.2,0.3,0.4)
    c * 2 should be(Color(0.4,0.6,0.8))
    c / 2 should be(Color(0.1,0.15,0.2))
  }

  it should "multiply a color" in {
    val c1 = Color(1,0.2,0.4)
    val c2 = Color(0.9,1,0.1)
    c1 * c2 should be(Color(0.9,0.2,0.04))
  }
}
