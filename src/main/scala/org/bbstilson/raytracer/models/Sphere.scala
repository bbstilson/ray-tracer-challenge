package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MatrixUtils.{ Matrix, identity }

import Math.{ pow, sqrt }

case class Sphere(
  transform: MatrixDouble = new MatrixDouble(identity),
  material: Material = Material()
) extends Interactable {

  def intersect(r: Ray): Seq[Intersection] = {
    val ray = r.transform(transform.inverse)
    // The vector from the sphere's center, to the ray origin.
    val sphereToRay = ray.origin - Point(0, 0, 0)
    val a = ray.direction.dot(ray.direction)
    val b = 2 * ray.direction.dot(sphereToRay)
    val c = sphereToRay.dot(sphereToRay) - 1
    val discriminant = pow(b, 2) - 4 * a * c

    discriminant match {
      case d if d < 0 => Seq.empty
      case d => {
        val x1 = (-b - sqrt(d)) / (2 * a)
        val x2 = (-b + sqrt(d)) / (2 * a)

        Seq(
          Intersection(x1, this),
          Intersection(x2, this)
        )
      }
    }
  }

  def normalAt(p: Point): SceneVector = {
    val objectPoint = transform.inverse * p
    val objectNormal = objectPoint - Point(0, 0, 0)
    val worldNormal = transform.inverse.transpose * objectNormal
    // worldNormal.w ← 0
    worldNormal.normalize
  }
}
