package org.bbstilson.raytracer.utils

object MathUtils {
  val PRECISION = 0.00001

  implicit class DoubleComparison(val x: Double) {
    def ~=(y: Double) = (x - y).abs < PRECISION
  }
}
