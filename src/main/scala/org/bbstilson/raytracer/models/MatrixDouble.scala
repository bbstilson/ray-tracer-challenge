package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.utils.MathUtils._

import MatrixDouble._

class MatrixDouble(vec: Matrix) {

  private val rows = vec.size
  private val cols = vec(0).size

  def row(i: Int): Row = vec(i)
  def col(i: Int): Row = vec.map(r => r(i))

  def apply(i: Int, j: Int): Double = vec(i)(j)

  def *(other: MatrixDouble): MatrixDouble = {
    val oTransposed = other.transpose
    val cols = (0 until oTransposed.cols).toVector.map(i => oTransposed.row(i))
    new MatrixDouble(
      for (row <- vec) yield
        for (col <- cols) yield
          dotProduct(row, col)
    )
  }

  def *(r: Row): Row = {
    for (row <- vec) yield dotProduct(row, r)
  }

  def *(p: Point): Point = {
    val Vector(x, y, z, _) = this * Vector(p.x, p.y, p.z, 1)
    Point(x, y, z)
  }

  def *(sv: SceneVector): SceneVector = {
    val Vector(x, y, z, _) = this * Vector(sv.x, sv.y, sv.z, 1)
    SceneVector(x, y, z)
  }

  def +(other: MatrixDouble): MatrixDouble = {
    mkMatrix(rows, cols, (r, c) => this(r,c) + other(r,c))
  }

  def -(other: MatrixDouble): MatrixDouble = {
    mkMatrix(rows, cols, (r, c) => this(r,c) - other(r, c))
  }

  def transpose: MatrixDouble = new MatrixDouble(vec.transpose)

  def determinant: Double = {
    if (rows == 2) {
      vec(0)(0) * vec(1)(1) - vec(0)(1) * vec(1)(0)
    } else {
      (0 until rows)
        .map(c => this(0, c) * cofactor(0, c))
        .reduceLeft(_ + _)
    }
  }

  def submatrix(r: Int, c: Int): MatrixDouble = {
    new MatrixDouble(drop1(vec, r).map { drop1(_, c) })
  }

  def minor(r: Int, c: Int): Double = submatrix(r, c).determinant

  def cofactor(r: Int, c: Int): Double = {
    val m = minor(r, c)
    if ((r + c) % 2 == 0) m else -m
  }

  def isInvertible: Boolean = determinant != 0

  def inverse: MatrixDouble = {
    require(isInvertible, s"Matrix is not invertible.\n $vec")

    // Cache determinant.
    val originalDeterminate = determinant

    new MatrixDouble(
      (0 until rows).toVector.map { r =>
        (0 until cols).toVector.map { c =>
          cofactor(r, c) / originalDeterminate
        }
      }.transpose
    )
  }

  def ~=(other: MatrixDouble): Boolean = {
    (0 until rows).forall { r =>
      (0 until cols).forall { c =>
        this(r, c) ~= other(r, c)
      }
    }
  }

  override def equals(other: Any): Boolean = other match {
    case o: MatrixDouble => this ~= o
  }

  override def toString: String = {
    "\n" + vec.map { r => r.mkString("\t") }.mkString("\n") + "\n"
  }
}

object MatrixDouble {
  type Row = Vector[Double]
  type Matrix = Vector[Row]

  def mkMatrix(rs: Int, cs: Int, f: (Int, Int) => Double): MatrixDouble = {
    new MatrixDouble(
      for (r <- (0 until rs).toVector) yield
        for (c <- (0 until cs).toVector) yield
          f(r, c)
    )
  }

  def identity(size: Int): MatrixDouble =
    mkMatrix(size, size, (r, c) => if (r == c) 1d else 0d)

  private def dotProduct(a: Row, b: Row): Double = {
    a
      .zip(b)
      .map({ case (n, m) => n * m })
      .reduceLeft(_ + _)
  }

  private def drop1[A](xs: Vector[A], i: Int): Vector[A] = {
    val size = xs.size
    (i, size) match {
      case (0, _) => xs.tail
      case _ if (i + 1 == size) => xs.init
      case _ => xs.dropRight(size - i) ++ xs.drop(i + 1)
    }
  }
}
