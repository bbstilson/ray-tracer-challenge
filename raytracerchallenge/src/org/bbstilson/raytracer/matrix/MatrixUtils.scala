package org.bbstilson.raytracer.matrix

object MatrixUtils {
  type Row = Vector[Double]
  type Matrix = Vector[Row]

  def mkMatrix(populate: (Int, Int, Double) => Double): Matrix = {
    getIdentityMatrix.map(_.zipWithIndex).zipWithIndex.map {
      case (row, r) => row.map { case (v, c) => populate(r, c, v) }
    }
  }

  def getIdentityMatrix: Matrix = {
    Vector
      .fill(4)(Vector.fill(4)(0d))
      .map(_.zipWithIndex)
      .zipWithIndex
      .map {
        case (col, c) =>
          col.map {
            case (_, r) => if (r == c) 1d else 0d
          }
      }
  }
}
