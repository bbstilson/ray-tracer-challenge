package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.view._
import org.bbstilson.raytracer.UnitSpec

class PointLightSpec extends UnitSpec {

  it should "have a position and intensity" in {
    val intensity = Color(1, 1, 1)
    val position = Point(0, 0, 0)
    val light = PointLight(position, intensity)
    light.position should be(position)
    light.intensity should be(intensity)
  }
}
