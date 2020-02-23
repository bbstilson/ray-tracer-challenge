package org.bbstilson.raytracer.view

import org.bbstilson.raytracer.utils.MathUtils._

import Math.{ceil}

case class Color(r: Double, g: Double, b: Double) {
  def +(v: Color): Color = Color(r + v.r, g + v.g, b + v.b)
  def -(v: Color): Color = Color(r - v.r, g - v.g, b - v.b)
  def *(c: Color): Color = Color(r * c.r, g * c.g, b * c.b)
  def *(s: Double): Color = Color(r * s, g * s, b * s)
  def /(s: Double): Color = Color(r / s, g / s, b / s)

  override def toString: String = {
    List(r, g, b).map(Color.stringifyValue).mkString(" ")
  }

  override def equals(other: Any) = other match {
    case c: Color => (r ~= c.r) && (g ~= c.g) && (b ~= c.b)
    case _        => false
  }
}

object Color {
  val BLACK = Color(0, 0, 0)
  val WHITE = Color(1, 1, 1)

  private def stringifyValue(v: Double): Int = {
    ceil(if (v > 1) 255 else if (v < 0) 0 else v * 255).toInt
  }
}
