package org.bbstilson.raytracer.models

trait Interactable {
  val material: Material
  def normalAt(p: Point): SceneVector
}
