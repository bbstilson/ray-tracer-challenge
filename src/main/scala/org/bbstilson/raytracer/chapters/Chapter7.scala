package org.bbstilson.raytracer.chapters

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.transforms._
import org.bbstilson.raytracer.shapes._
import org.bbstilson.raytracer.view._

object Chapter7 {

  def main(args: Array[String]): Unit = {
    val startNs = System.currentTimeMillis()

    val wallScale = Scale(10, 0.01, 10)
    val wallMaterial = Material(color = Color(1, 0.9, 0.9), specular = 0)
    val floor = Sphere(wallScale, wallMaterial)
    val leftWall = Sphere(
      List(Translation(0, 0, 5), RotationY(-Math.PI / 4), RotationX(Math.PI / 2), wallScale),
      wallMaterial
    )
    val rightWall = Sphere(
      List(Translation(0, 0, 5), RotationY(Math.PI / 4), RotationX(Math.PI / 2), wallScale),
      wallMaterial
    )
    val middleBall = Sphere(
      Translation(-0.5, 1, 0.5),
      Material(color = Color(0.1, 1, 0.5), diffuse = 0.7, specular = 0.3)
    )
    val rightBall = Sphere(
      List(Translation(1.5, 0.5, -0.5), Scale(0.5, 0.5, 0.5)),
      Material(
        color = Color(0.5, 1, 0.1),
        diffuse = 0.7,
        specular = 0.3
      )
    )
    val leftBall = Sphere(
      List(Translation(-1.5, 0.33, -0.75), Scale(0.33, 0.33, 0.33)),
      Material(color = Color(1, 0.8, 0.1), diffuse = 0.7, specular = 0.3)
    )
    val light = PointLight(Point(-10, 10, -10), Color.WHITE)
    val world = World(
      List(floor, leftWall, rightWall, leftBall, middleBall, rightBall),
      Some(light)
    )
    val view = ViewTransform(Point(0, 1.5, -5), Point(0, 1, 0), SceneVector(0, 1, 0))
    val camera = Camera(100, 50, Math.PI / 3, view)

    camera.render(world).save

    println(s"Finished in ${System.currentTimeMillis() - startNs}ms.")
  }
}
