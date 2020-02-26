package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MathUtils._

case class Point(x: Double, y: Double, z: Double) {
  def +(v: Vector3): Point = Point(x + v.x, y + v.y, z + v.z)
  def -(p: Point): Vector3 = Vector3(x - p.x, y - p.y, z - p.z)
  def -(v: Vector3): Point = Point(x - v.x, y - v.y, z - v.z)
  def unary_- = Point(-x, -y, -z)

  override def equals(other: Any) = other match {
    case c: Point => (x ~= c.x) && (y ~= c.y) && (z ~= c.z)
    case _        => false
  }
}
