package org.bbstilson.raytracer.models

object VectorToMatrix {
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

    def determinant: Double = m(0)(0) * m(1)(1) - m(0)(1) * m(1)(0)

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
