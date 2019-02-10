package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

import Math.sqrt

class MaterialSpec extends UnitSpec {
  behavior of "Material"

  val m = Material()
  val position = Point(0, 0, 0)

  it should "light with the eye between the light and the surface" in {
    val eye = SceneVector(0, 0, -1)
    val normal = SceneVector(0, 0, -1)
    val light = PointLight(Point(0, 0, -10), Color(1, 1, 1))
    val result = m.light(light, position, eye, normal)
    result should be(Color(1.9, 1.9, 1.9))
  }

  it should "light with the eye between light and surface, eye offset 45°" in {
    val eye = SceneVector(0, sqrt(2)/2, -sqrt(2)/2)
    val normal = SceneVector(0, 0, -1)
    val light = PointLight(Point(0, 0, -10), Color(1, 1, 1))
    val result = m.light(light, position, eye, normal)
    result should be(Color(1.0, 1.0, 1.0))
  }

  it should "light with eye opposite surface, light offset 45°" in {
    val eye = SceneVector(0, 0, -1)
    val normal = SceneVector(0, 0, -1)
    val light = PointLight(Point(0, 10, -10), Color(1, 1, 1))
    val result = m.light(light, position, eye, normal)
    result should be(Color(0.7364, 0.7364, 0.7364))
  }

  it should "light with eye in the path of the reflection vector" in {
    val eye = SceneVector(0, -sqrt(2)/2, -sqrt(2)/2)
    val normal = SceneVector(0, 0, -1)
    val light = PointLight(Point(0, 10, -10), Color(1, 1, 1))
    val result = m.light(light, position, eye, normal)
    result should be(Color(1.6364, 1.6364, 1.6364))
  }

  it should "with the light behind the surface" in {
    val eye = SceneVector(0, 0, -1)
    val normal = SceneVector(0, 0, -1)
    val light = PointLight(Point(0, 0, 10), Color(1, 1, 1))
    val result = m.light(light, position, eye, normal)
    result should be(Color(0.1, 0.1, 0.1))
  }
}
