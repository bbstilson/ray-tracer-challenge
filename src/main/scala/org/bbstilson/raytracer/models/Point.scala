package org.bbstilson.raytracer.models

case class Point(x: Double, y: Double, z: Double) {
  def +(v: Vector): Point = Point(x + v.x, y + v.y, z + v.z)
  def -(p: Point): Vector = Vector(x - p.x, y - p.y, z - p.z)
  def -(v: Vector): Point = Point(x - v.x, y - v.y, z - v.z)
  def unary_- = Point(-x, -y, -z)
}
