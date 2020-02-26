package org.bbstilson.raytracer.models

import Ordering.Double.TotalOrdering

final case class Intersection(t: Double, o: Interactable) {

  def prepareComputations(r: Ray): IntersectionComputations = {
    val point = r.position(t)
    val eyeV = -r.direction
    val normalV = o.normalAt(point)
    val (inside, finalNormal) = if (normalV.dot(eyeV) < 0) {
      (true, -normalV)
    } else {
      (false, normalV)
    }
    IntersectionComputations(t, o, point, eyeV, finalNormal, inside)
  }
}

final case class IntersectionComputations(
  t: Double,
  o: Interactable,
  point: Point,
  eyeV: Vector3,
  normalV: Vector3,
  inside: Boolean
)

object Intersection {

  def hit(xs: List[Intersection]): Option[Intersection] = {
    xs.filter(_.t > 0)
      .sortBy(_.t)
      .headOption
  }
}
