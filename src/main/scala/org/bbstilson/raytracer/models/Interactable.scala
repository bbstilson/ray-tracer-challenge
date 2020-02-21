package org.bbstilson.raytracer.models

trait Interactable {
  def material: Material
  def normalAt(p: Point): SceneVector
  def intersect(r: Ray): List[Intersection]
}
