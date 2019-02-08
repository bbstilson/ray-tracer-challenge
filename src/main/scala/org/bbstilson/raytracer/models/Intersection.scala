package org.bbstilson.raytracer.models

case class Intersection(t: Double, o: Interactable)

object Intersection {
  def hit(xs: Seq[Intersection]): Option[Intersection] = {
    xs
      .filter(_.t > 0)
      .sortBy(_.t)
      .headOption
  }
}
