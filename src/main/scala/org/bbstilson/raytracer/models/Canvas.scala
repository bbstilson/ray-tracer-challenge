package org.bbstilson.raytracer.models

case class FoldState(
  output: Seq[String],
  nextLine: String,
  carryOver: Option[String]
)

object FoldState {
  val empty = FoldState(Seq.empty[String], "", None)
}

class Canvas(val width: Int, val height: Int) {

  // Mutable variable with map of pixels.
  var pixels: Map[(Int, Int), Color] = {
    val ps = for {
      w <- (0 to width - 1)
      h <- (0 to height - 1)
    } yield (w, h)

    ps
      .map(t => (t, Color.empty))
      .toMap
  }

  def writePixel(x: Int, y: Int, c: Color): Unit = {
    val pos = (x, y)
    pixelAt(pos) match {
      case Some(_) => pixels = pixels + (pos -> c)
      case None => throw new Exception("Tried to add a color that is not in the canvas.")
    }
  }

  def pixelAt(pos: (Int, Int)): Option[Color] = pixels.get(pos)

  def toPPM: Seq[String] = {
    val header = Seq(
      "P3",
      s"$width $height",
      "255"
    )

    val body = (0 to height - 1)
      .map(h => (0 to width - 1)
        .map(w => pixels((w, h)).toString).mkString(" ")
      )
      .flatMap(chopLine)

    header ++ body :+ "\n"
  }

  private def chopLine(line: String): Seq[String] = {
    if (line.length > Canvas.MAX_PPM_LINE_LENGTH) {
      val (head, rest) = line.splitAt(
        line.lastIndexWhere(_.isSpaceChar, Canvas.MAX_PPM_LINE_LENGTH)
      )
      Seq(head) ++ chopLine(rest.trim)
    } else {
      Seq(line)
    }
  }
}

object Canvas {
  val MAX_PPM_LINE_LENGTH = 70
}
