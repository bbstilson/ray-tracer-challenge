package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MathUtils._

object MatrixUtils {
  type Row = Vector[Double]
  type Matrix = Vector[Row]

  object Matrix {
    def identity(size: Int): Matrix = {
      (0 to size - 1).map { i =>
        (0 to size - 1).map { j =>
          if (i == j) 1d else 0d
        }.toVector
      }.toVector
    }
  }

  implicit class RichMatrix(val m: Matrix) extends AnyVal {
    def apply(m: Matrix) = RichMatrix(m)

    def *(other: Matrix): Matrix = RichMatrix.mXm(this.m, other.m)

    // DummyImplicit needed because the Scala/JVM is dumb:
    // See: https://stackoverflow.com/a/17842474/6147439
    def *(other: Row)(implicit d: DummyImplicit): Row =
      RichMatrix.mXr(this.m, other.r)

    def determinant: Double = {
      if (m.size == 2) {
        m(0)(0) * m(1)(1) - m(0)(1) * m(1)(0)
      } else {
        (0 to m.size - 1)
          .map(col => m(0)(col) * m.cofactor(0, col))
          .reduceLeft(_ + _)
      }
    }

    def submatrix(row: Int, col: Int): Matrix = {
      RichMatrix
        .drop1(m, row)
        .map { RichMatrix.drop1(_, col) }
    }

    def minor(r: Int, c: Int): Double = m.submatrix(r, c).determinant

    def cofactor(r: Int, c: Int): Double = {
      val minor = m.minor(r, c)
      if (r + c % 2 == 0) minor else -minor
    }

    def isInvertible: Boolean = m.determinant != 0

    def inverse: Matrix = {
      require(m.isInvertible)

      val s = m.size - 1
      val originalDeterminate = m.determinant

      (0 to s).map { row =>
        (0 to s).map { col =>
          m.cofactor(row, col) / originalDeterminate
        }.toVector
      }.toVector.transpose
    }

    override def equals(other: Any): Boolean = other match {
      case c: Matrix => {
        val s = m.size - 1
        (0 to s).forall { row =>
          (0 to s).forall { col =>
            this.m(row)(col) ~= c(row)(col)
          }
        }
      }
      case _ => false
    }

    override def toString: String = {
      "\n" + m.map { r => r.mkString("\t") }.mkString("\n") + "\n"
    }
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

    def mXr(m: Matrix, r: Row): Row = {
      for (row <- m) yield RichRow.dotProduct(row, r)
    }

    def drop1[A](xs: Vector[A], i: Int): Vector[A] = {
      val size = xs.size
      (i, size) match {
        case (0, _) => xs.tail
        case _ if (i + 1 == size) => xs.init
        case _ => xs.dropRight(size - i) ++ xs.drop(i + 1)
      }
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
