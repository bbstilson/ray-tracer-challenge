package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MathUtils._

import Math.{pow, sqrt}

case class Vector3(x: Double, y: Double, z: Double) {
  def +(v: Vector3): Vector3 = Vector3(x + v.x, y + v.y, z + v.z)
  def -(v: Vector3): Vector3 = Vector3(x - v.x, y - v.y, z - v.z)
  def *(s: Double): Vector3 = Vector3(x * s, y * s, z * s)
  def /(s: Double): Vector3 = Vector3(x / s, y / s, z / s)
  def unary_- = Vector3(-x, -y, -z)
  def dot(v: Vector3): Double = x * v.x + y * v.y + z * v.z
  def magnitude: Double = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2))

  def cross(v: Vector3): Vector3 = {
    Vector3(
      y * v.z - z * v.y,
      z * v.x - x * v.z,
      x * v.y - y * v.x
    )
  }

  def normalize: Vector3 = {
    val m = this.magnitude
    require(m > 0)
    Vector3(x / m, y / m, z / m)
  }

  override def equals(other: Any) = other match {
    case c: Vector3 => (x ~= c.x) && (y ~= c.y) && (z ~= c.z)
    case _          => false
  }
}

object Vector3 {

  def reflect(in: Vector3, normal: Vector3): Vector3 = {
    in - normal * 2 * in.dot(normal)
  }
}
