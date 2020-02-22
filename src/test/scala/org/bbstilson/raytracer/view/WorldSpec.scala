package org.bbstilson.raytracer.view

import org.bbstilson.raytracer.TestUtils.WorldUtils
import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.shapes._
import org.bbstilson.raytracer.transforms._
import org.bbstilson.raytracer.UnitSpec

class WorldSpec extends UnitSpec with WorldUtils {

  it should "create an empty world" in {
    val w = World()
    w.objects should be(empty)
    w.light should be(empty)
  }

  "intersect" should "return a list of intersections" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    val xs = w.intersect(r)

    xs.size shouldBe 4
    xs(0).t shouldBe 4
    xs(1).t shouldBe 4.5
    xs(2).t shouldBe 5.5
    xs(3).t shouldBe 6
  }

  "shadeHit" should "shade an intersection" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    val shape = w.objects.head
    val i = Intersection(4, shape)
    val c = i.prepareComputations(r)
    w.shadeHit(c) shouldBe Color(0.38066, 0.47583, 0.2855)
  }

  it should "shade an intersection from the inside" in {
    val w = World(List(s1, s2), Some(PointLight(Point(0, 0.25, 0), Color(1, 1, 1))))
    val r = Ray(Point(0, 0, 0), SceneVector(0, 0, 1))
    val shape = w.objects.drop(1).head
    val i = Intersection(0.5, shape)
    val c = i.prepareComputations(r)
    w.shadeHit(c) shouldBe Color(0.90498, 0.90498, 0.90498)
  }

  "colorAt" should "color when a ray misses" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 1, 0))
    w.colorAt(r) shouldBe Color(0, 0, 0)
  }

  it should "color when a ray hits" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    w.colorAt(r) shouldBe Color(0.38066, 0.47583, 0.2855)
  }

  it should "return the excepted color from inside a sphere" in {
    val outer = s1.copy(
      material = s1.material.copy(ambient = 1d)
    )
    val inner = s2.copy(
      material = s2.material.copy(ambient = 1d)
    )
    val w = World(List(outer, inner), Some(light))
    val r = Ray(Point(0, 0, 0.75), SceneVector(0, 0, -1))

    w.colorAt(r) shouldBe inner.material.color
  }
}
