package org.bbstilson.raytracer.models

case class Ray(origin: Point, direction: SceneVector) {
  def position(t: Double): Point = origin + direction * t
  def transform(m: MatrixDouble): Ray = Ray(m * origin, m * direction)
}
