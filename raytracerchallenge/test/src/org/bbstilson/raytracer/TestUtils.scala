package org.bbstilson.raytracer

import org.bbstilson.raytracer.models._
import org.bbstilson.raytracer.transforms._
import org.bbstilson.raytracer.shapes._
import org.bbstilson.raytracer.view._

object TestUtils {

  trait WorldUtils {

    val s1 = Sphere(
      material = Material(
        color = Color(0.8, 1.0, 0.6),
        diffuse = 0.7,
        specular = 0.2
      )
    )

    val s2 = Sphere(
      transform = Scale(0.5, 0.5, 0.5)
    )

    val light = PointLight(
      Point(-10, 10, -10),
      Color(1, 1, 1)
    )

    val w = World(List(s1, s2), Some(light))
  }
}
