package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

import Math.{ PI, sqrt }

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

  it should "compute the normal at a point on the x axis" in {
    val s = Sphere()
    val n = s.normalAt(Point(1, 0, 0))
    n should be(SceneVector(1, 0, 0))
  }

  it should "compute the normal at a point on the y axis" in {
    val s = Sphere()
    val n = s.normalAt(Point(0, 1, 0))
    n should be(SceneVector(0, 1, 0))
  }

  it should "compute the normal at a point on the z axis" in {
    val s = Sphere()
    val n = s.normalAt(Point(0, 0, 1))
    n should be(SceneVector(0, 0, 1))
  }

  it should "compute the normal at a non-axial point" in {
    val s = Sphere()
    val n = s.normalAt(Point(sqrt(3)/3, sqrt(3)/3, sqrt(3)/3))
    n should be(SceneVector(sqrt(3)/3, sqrt(3)/3, sqrt(3)/3))
  }

  it should "have a normalized normal" in {
    val s = Sphere()
    val n = s.normalAt(Point(sqrt(3)/3, sqrt(3)/3, sqrt(3)/3))
    n should be(n.normalize)
  }

  it should "compute the normal on a translated sphere" in {
    val s = Sphere(Translation(0, 1, 0))
    val n = s.normalAt(Point(0, 1.70711, -0.70711))
    n should be(SceneVector(0, 0.70711, -0.70711))
  }

  it should "compute the normal on a transformed sphere" in {
    val s = Sphere(Scale(1, 0.5, 1) * RotationZ(PI/5))
    val n = s.normalAt(Point(0, sqrt(2)/2, -sqrt(2)/2))
    n should be(SceneVector(0, 0.97014, -0.24254))
  }

  it should "have a default material" in {
    val s = Sphere()
    s.material should be(Material())
  }

  it should "be created with a material" in {
    val m = Material(ambient = 1d)
    val s = Sphere(mat = m)
    s.material should be(m)
  }
}
