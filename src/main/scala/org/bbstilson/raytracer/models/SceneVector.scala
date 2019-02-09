package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MathUtils._

import Math.{ pow, sqrt }

case class SceneVector(x: Double, y: Double, z: Double) {
  def +(v: SceneVector): SceneVector = SceneVector(x + v.x, y + v.y, z + v.z)
  def -(v: SceneVector): SceneVector = SceneVector(x - v.x, y - v.y, z - v.z)
  def *(s: Double): SceneVector = SceneVector(x * s, y * s, z * s)
  def /(s: Double): SceneVector = SceneVector(x / s, y / s, z / s)
  def unary_- = SceneVector(-x, -y, -z)
  def dot(v: SceneVector): Double = x * v.x + y * v.y + z * v.z
  def magnitude: Double = sqrt(pow(x, 2) + pow(y, 2) + pow(z, 2))

  def cross(v: SceneVector): SceneVector = {
    SceneVector(
      y * v.z - z * v.y,
      z * v.x - x * v.z,
      x * v.y - y * v.x
    )
  }

  def normalize: SceneVector = {
    val m = this.magnitude
    require(m > 0)
    SceneVector(x / m, y / m, z / m)
  }

  override def equals(other: Any) = other match {
    case c: SceneVector => (x ~= c.x) && (y ~= c.y) && (z ~= c.z)
    case _ => false
  }
}

object SceneVector {
  def reflect(in: SceneVector, normal: SceneVector): SceneVector = {
    in - normal * 2 * in.dot(normal)
  }
}
