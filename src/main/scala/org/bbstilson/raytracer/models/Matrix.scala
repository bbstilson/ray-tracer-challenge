package org.bbstilson.raytracer.models

object VectorToMatrix {
  type Row = Vector[Double]
  type Matrix = Vector[Row]

  implicit class RichMatrix(val m: Matrix) extends AnyVal {
    def apply(m: Matrix) = RichMatrix(m)

    def *(other: Matrix): Matrix = RichMatrix.mXm(this.m, other.m)
  }

  implicit class RichRow(val r: Row) extends AnyVal {
    def apply(r: Row) = RichRow(r)
  }

  object RichMatrix {
    def mXm(m1: Matrix, m2: Matrix): Matrix = {
      for (row <- m1) yield
        for (col <- m2.transpose) yield
          RichRow.dotProduct(row, col)
    }
  }

  object RichRow {
    def dotProduct(a: Row, b: Row): Double = {
      a
        .zip(b)
        .map({ case (n, m) => n * m })
        .reduceLeft(_ + _)
    }
  }
}
