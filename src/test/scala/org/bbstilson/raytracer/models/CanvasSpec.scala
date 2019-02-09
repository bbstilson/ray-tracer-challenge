package org.bbstilson.raytracer.models

import org.bbstilson.raytracer.UnitSpec

class CanvasSpec extends UnitSpec {
  behavior of "Canvas"

  it should "initialize with every pixel black" in {
    val c = new Canvas(10, 20)
    c.width should be(10)
    c.height should be(20)
    all(c.pixels.values) should be(Color.BLACK)
  }

  it should "write pixels" in {
    val c = new Canvas(10, 20)
    val red = Color(1,0,0)
    c.writePixel(2, 3, red)
    c.pixelAt(2,3) should be(Some(red))
  }

  "toPPM" should "write the header" in {
    val c = new Canvas(5, 3)
    val ppm = c.toPPM
    ppm.take(3) should be(Seq("P3", "5 3", "255"))
  }

  it should "write pixel data" in {
    val c = new Canvas(5, 3)
    val c1 = Color(1.5,0,0)
    val c2 = Color(0,0.5,0)
    val c3 = Color(-0.5,0,1)
    c.writePixel(0,0,c1)
    c.writePixel(2,1,c2)
    c.writePixel(4,2,c3)
    val ppm = c.toPPM
    ppm.drop(3).take(3) should be(Seq(
      "255 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
      "0 0 0 0 0 0 0 128 0 0 0 0 0 0 0",
      "0 0 0 0 0 0 0 0 0 0 0 0 0 0 255"
    ))
  }

  it should "split long lines foo" in {
    val c = new Canvas(10, 2)
    // Fill every pixel in the canvas with a color.
    (0 to 9).foreach({ x =>
      (0 to 1).foreach({ y => {
        c.writePixel(x, y, Color(1, 0.8, 0.6))
      }})
    })
    val ppm = c.toPPM
    ppm.drop(3).take(4) should be(Seq(
      "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204",
      "153 255 204 153 255 204 153 255 204 153 255 204 153",
      "255 204 153 255 204 153 255 204 153 255 204 153 255 204 153 255 204",
      "153 255 204 153 255 204 153 255 204 153 255 204 153"
    ))
  }

  it should "end with a newline" in {
    val c = new Canvas(5, 3)
    val ppm = c.toPPM
    ppm.last should be("\n")
  }
}
