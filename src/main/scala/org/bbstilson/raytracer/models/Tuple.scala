package org.bbstilson.raytracer.models

import Math.{ pow, sqrt }

sealed trait Tuple

case class Point(x: Double, y: Double, z: Double) extends Tuple {
  def +(v: Vector): Point = Point(x + v.x, y + v.y, z + v.z)
  def -(p: Point): Vector = Vector(x - p.x, y - p.y, z - p.z)
  def -(v: Vector): Point = Point(x - v.x, y - v.y, z - v.z)
  def unary_- = Point(-x, -y, -z)
}

case class Vector(x: Double, y: Double, z: Double) extends Tuple {
  def +(v: Vector): Vector = Vector(x + v.x, y + v.y, z + v.z)
  def -(v: Vector): Vector = Vector(x - v.x, y - v.y, z - v.z)
  def *(s: Double): Vector = Vector(x * s, y * s, z * s)
  def /(s: Double): Vector = Vector(x / s, y / s, z / s)
  def unary_- = Vector(-x, -y, -z)
  def dot(v: Vector): Double = x * v.x + y * v.y + z * v.z
  def magnitude: Double = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2))
  def cross(v: Vector): Vector = {
    Vector(
      y * v.z - z * v.y,
      z * v.x - x * v.z,
      x * v.y - y * v.x
    )
  }
  def normalize: Vector = {
    val m = this.magnitude
    require(m > 0)
    Vector(x / m, y / m, z / m)
  }
}
