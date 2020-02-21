package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class IntersectionSpec extends UnitSpec {

  it should "encapsulate t and Interactable object" in {
    val s = Sphere()
    val i = Intersection(3.5, s)
    i.t should be(3.5)
    i.o should be(s)
  }

  it should "hit when all intersections have positive t" in {
    val s = Sphere()
    val i1 = Intersection(1, s)
    val i2 = Intersection(2, s)
    val intersections = Seq(i2, i1)
    val i = Intersection.hit(intersections)
    i should be(Some(i1))
  }

  it should "hit when some intersections have negative t" in {
    val s = Sphere()
    val i1 = Intersection(-1, s)
    val i2 = Intersection(1, s)
    val intersections = Seq(i2, i1)
    val i = Intersection.hit(intersections)
    i should be(Some(i2))
  }

  it should "not hit when all intersections have negative t" in {
    val s = Sphere()
    val i1 = Intersection(-2, s)
    val i2 = Intersection(-1, s)
    val intersections = Seq(i2, i1)
    val i = Intersection.hit(intersections)
    i should be(None)
  }

  it should "hit is always the lowest non-negative intersection" in {
    val s = Sphere()
    val i1 = Intersection(5, s)
    val i2 = Intersection(7, s)
    val i3 = Intersection(-3, s)
    val i4 = Intersection(2, s)
    val intersections = Seq(i1, i2, i3, i4)
    val i = Intersection.hit(intersections)
    i should be(Some(i4))
  }
}
