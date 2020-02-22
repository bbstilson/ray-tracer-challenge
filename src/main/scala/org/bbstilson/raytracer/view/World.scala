package org.bbstilson.raytracer.view

import org.bbstilson.raytracer.models._

import Ordering.Double.TotalOrdering

final case class World(
  objects: List[Interactable] = Nil,
  light: Option[PointLight] = None
) {

  def intersect(r: Ray): List[Intersection] = {
    objects.flatMap(_.intersect(r)).sortBy(_.t)
  }

  def shadeHit(c: IntersectionComputations): Color = {
    light match {
      case Some(l) => c.o.material.light(l, c.point, c.eyeV, c.normalV)
      case None    => throw new Exception("Tried to render a scene without any lights!")
    }
  }

  def colorAt(r: Ray): Color = {
    Intersection
      .hit(intersect(r))
      .map(_.prepareComputations(r))
      .map(shadeHit _)
      .getOrElse(Color.BLACK)
  }
}
