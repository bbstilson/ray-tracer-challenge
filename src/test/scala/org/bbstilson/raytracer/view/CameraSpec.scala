package org.bbstilson.raytracer.view

import org.bbstilson.raytracer.UnitSpec
import org.bbstilson.raytracer.matrix._
import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.transforms._
import org.bbstilson.raytracer.utils.MathUtils._
import org.bbstilson.raytracer.TestUtils.WorldUtils

class CameraSpec extends UnitSpec with WorldUtils {

  it should "construct a camera" in {
    val c = Camera(160, 120, Math.PI / 2)
    c.hSize shouldBe 160
    c.vSize shouldBe 120
    c.fieldOfView shouldBe Math.PI / 2
    c.transform shouldBe new MatrixDouble(MatrixUtils.getIdentityMatrix)
  }

  it should "know the size of the pixels on the canvas" in {
    val c1 = Camera(200, 125, Math.PI / 2)
    val c2 = Camera(125, 200, Math.PI / 2)
    assert(c1.pixelSize ~= 0.01)
    assert(c2.pixelSize ~= 0.01)
  }

  "rayForPixel" should "cast a ray through the center" in {
    val c = Camera(201, 101, Math.PI / 2)
    val r = c.rayForPixel(100, 50)
    r.origin shouldBe Point(0, 0, 0)
    r.direction shouldBe SceneVector(0, 0, -1)
  }

  it should "cast a ray through the corner" in {
    val c = Camera(201, 101, Math.PI / 2)
    val r = c.rayForPixel(0, 0)
    r.origin shouldBe Point(0, 0, 0)
    r.direction shouldBe SceneVector(0.66519, 0.33259, -0.66851)
  }

  it should "cast a ray when the camera is transformed" in {
    val t = RotationY(Math.PI / 4) * Translation(0, -2, 5)
    val c = Camera(201, 101, Math.PI / 2, t)
    val r = c.rayForPixel(100, 50)
    r.origin shouldBe Point(0, 2, -5)
    r.direction shouldBe SceneVector(Math.sqrt(2) / 2, 0, -Math.sqrt(2) / 2)
  }

  "render" should "render a world" in {
    val from = Point(0, 0, -5)
    val to = Point(0, 0, 0)
    val up = SceneVector(0, 1, 0)
    val c = Camera(11, 11, Math.PI / 2, ViewTransform(from, to, up))
    c.render(w).pixelAt(5, 5) shouldBe Some(Color(0.38066, 0.47583, 0.2855))
  }
}
