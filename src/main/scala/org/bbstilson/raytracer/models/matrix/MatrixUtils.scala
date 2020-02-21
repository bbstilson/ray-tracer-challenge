package org.bbstilson.raytracer.matrix

object MatrixUtils {
  type Row = Vector[Double]
  type Matrix = Vector[Row]

  def mkMatrix(rs: Int, cs: Int, f: (Int, Int) => Double): Matrix = {
    for (r <- (0 until rs).toVector) yield
      for (c <- (0 until cs).toVector) yield
        f(r, c)
  }

  def identity: Matrix = {
    mkMatrix(4, 4, (r, c) => if (r == c) 1d else 0d)
  }
}
