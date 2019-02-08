package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class SphereSpec extends UnitSpec {
  behavior of "Sphere"

  it should "ray intersects a sphere at two points" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    val s = Sphere()
    val intersections = s.intersect(r)
    intersections.head.t should be(4.0)
    intersections.last.t should be(6.0)
  }

  it should "ray intersects a sphere at a tangent" in {
    val r = Ray(Point(0, 1, -5), SceneVector(0, 0, 1))
    val s = Sphere()
    s.intersect(r).foreach(_.t should be(5.0))
  }

  it should "ray misses a sphere" in {
    val r = Ray(Point(0, 2, -5), SceneVector(0, 0, 1))
    val s = Sphere()
    s.intersect(r) should be(Seq.empty)
  }

  it should "have two intersections if the ray starts inside a sphere" in {
    val r = Ray(Point(0, 0, 0), SceneVector(0, 0, 1))
    val s = Sphere()
    val intersections = s.intersect(r)
    intersections.head.t should be(-1.0)
    intersections.last.t should be(1.0)
  }

  it should "have two negative intersections if the ray is behind a sphere" in {
    val r = Ray(Point(0, 0, 5), SceneVector(0, 0, 1))
    val s = Sphere()
    val intersections = s.intersect(r)
    intersections.head.t should be(-6.0)
    intersections.last.t should be(-4.0)
  }

  it should "include the sphere on an intersect" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    val s = Sphere()
    s.intersect(r).foreach(_.o should be(s))
  }

  it should "intersect with a ray when scaled" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    val s = Sphere(Scale(2, 2, 2))
    val intersections = s.intersect(r)
    intersections.size should be(2)
    intersections.head.t should be(3)
    intersections.last.t should be(7)
  }

  it should "intersect with a ray when translated" in {
    val r = Ray(Point(0, 0, -5), SceneVector(0, 0, 1))
    val s = Sphere(Translation(5, 0, 0))
    s.intersect(r) should be(Seq.empty)
  }
}
