package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MathUtils._

import Math.{ ceil }

case class Color(r: Double, g: Double, b: Double) {
  def +(v: Color): Color = Color(r + v.r, g + v.g, b + v.b)
  def -(v: Color): Color = Color(r - v.r, g - v.g, b - v.b)
  def *(s: Double): Color = Color(r * s, g * s, b * s)
  def /(s: Double): Color = Color(r / s, g / s, b / s)
  def *(c: Color): Color = Color(r * c.r, g * c.g, b * c.b)

  override def toString: String = {
    val rV = if (r > 1) 255 else if (r < 0) 0 else r * 255
    val gV = if (g > 1) 255 else if (g < 0) 0 else g * 255
    val bV = if (b > 1) 255 else if (b < 0) 0 else b * 255
    s"${ceil(rV).toInt} ${ceil(gV).toInt} ${ceil(bV).toInt}"
  }

  override def equals(other: Any) = other match {
    case c: Color => (r ~= c.r) && (g ~= c.g) && (b ~= c.b)
    case _ => false
  }
}

object Color {
  val empty = Color(0,0,0)
}
