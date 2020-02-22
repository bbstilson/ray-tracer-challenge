package org.bbstilson.raytracer.view

import java.io.{File, PrintWriter}

case class Canvas(width: Int, height: Int) {

  // Mutable variable with map of pixels.
  // TODO: Make this a mutable map, too.
  var pixels: Map[(Int, Int), Color] = {
    val ps = for {
      w <- (0 to width - 1)
      h <- (0 to height - 1)
    } yield (w, h)

    ps.map(t => (t, Color.BLACK)).toMap
  }

  def writePixel(x: Int, y: Int, c: Color): Unit = {
    pixelAt(x, y) match {
      case Some(_) => pixels = pixels + ((x, y) -> c)
      case None    => // do nothing
    }
  }

  def pixelAt(x: Int, y: Int): Option[Color] = pixels.get((x, y))

  def toPPM: Seq[String] = {
    val header = Seq("P3", s"$width $height", "255")

    val body = (0 to height - 1)
      .map(h =>
        (0 to width - 1)
          .map(w => pixels((w, h)).toString)
          .mkString(" ")
      )
      .flatMap(chopLine)

    header ++ body :+ "\n"
  }

  def save: Unit = {
    val homeDir = System.getProperty("user.home")
    val outputFile = new File(s"${homeDir}/Desktop/${Canvas.getFileName}")
    val writer = new PrintWriter(outputFile)

    toPPM.foreach { line =>
      writer.write(line)
      writer.write("\n")
    }

    writer.close()
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

  def getFileName: String = s"canvas_${System.currentTimeMillis}.ppm"
}
