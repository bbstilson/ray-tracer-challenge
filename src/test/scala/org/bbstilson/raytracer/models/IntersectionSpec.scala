package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class IntersectionSpec extends UnitSpec {

  it should "encapsulate t and Interactable object" in {
    val s = Sphere()
    val i = Intersection(3.5, s)
    i.t should be(3.5)
    i.o should be(s)
  }

  "hit" should "hit when all intersections have positive t" in {
    val s = Sphere()
    val i1 = Intersection(1, s)
    val i2 = Intersection(2, s)
    val intersections = List(i2, i1)
    val i = Intersection.hit(intersections)
    i should be(Some(i1))
  }

  it should "hit when some intersections have negative t" in {
    val s = Sphere()
    val i1 = Intersection(-1, s)
    val i2 = Intersection(1, s)
    val intersections = List(i2, i1)
    val i = Intersection.hit(intersections)
    i should be(Some(i2))
  }

  it should "not hit when all intersections have negative t" in {
    val s = Sphere()
    val i1 = Intersection(-2, s)
    val i2 = Intersection(-1, s)
    val intersections = List(i2, i1)
    val i = Intersection.hit(intersections)
    i should be(None)
  }

  it should "hit is always the lowest non-negative intersection" in {
    val s = Sphere()
    val i1 = Intersection(5, s)
    val i2 = Intersection(7, s)
    val i3 = Intersection(-3, s)
    val i4 = Intersection(2, s)
    val intersections = List(i1, i2, i3, i4)
    val i = Intersection.hit(intersections)
    i should be(Some(i4))
  }

  "prepareComputations" should "precomputes intersection data" in {
    val r = Ray(Point(0,0,-5),SceneVector(0,0,1))
    val shape = Sphere()
    val i = Intersection(4, shape)

    val c = i.prepareComputations(r)
    c.t shouldBe i.t
    c.o shouldBe i.o
    c.point shouldBe Point(0,0,-1)
    c.eyeV shouldBe SceneVector(0,0,-1)
    c.normalV shouldBe SceneVector(0,0,-1)
  }

  it should "set inside to false when the interaction occurs on the outside" in {
    val r = Ray(Point(0,0,-5), SceneVector(0,0,1))
    val shape = Sphere()
    val i = Intersection(4, shape)
    val c = i.prepareComputations(r)
    c.inside shouldBe false
  }

  it should "set inside to true when the interaction occurs from the inside" in {
    val r = Ray(Point(0, 0, 0), SceneVector(0, 0, 1))
    val shape = Sphere()
    val i = Intersection(1, shape)
    val c = i.prepareComputations(r)
    c.point shouldBe Point(0, 0, 1)
    c.eyeV shouldBe SceneVector(0,0,-1)
    c.normalV shouldBe SceneVector(0,0,-1)
    c.inside shouldBe true
  }
}
