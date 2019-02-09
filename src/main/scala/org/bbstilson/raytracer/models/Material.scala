package org.bbstilson.raytracer.models

case class Material(
  color: Color,
  ambient: Double,
  diffuse: Double,
  specular: Double,
  shininess: Double
)

object Material {
  def apply(
    color: Color = Color(1, 1, 1),
    ambient: Double = 0.1,
    diffuse: Double = 0.9,
    specular: Double = 0.9,
    shininess: Double = 200.0
  ): Material = {
    require(ambient >= 0, "Material:ambient cannot be a negative values.")
    require(diffuse >= 0, "Material:diffuse cannot be a negative values.")
    require(specular >= 0, "Material:specular cannot be a negative values.")
    require(shininess >= 0, "Material:shininess cannot be a negative values.")
    Material(color, ambient, diffuse, specular, shininess)
  }
}
