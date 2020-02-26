package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.view.Material

trait Interactable {
  def material: Material
  def normalAt(p: Point): Vector3
  def intersect(r: Ray): List[Intersection]
}
