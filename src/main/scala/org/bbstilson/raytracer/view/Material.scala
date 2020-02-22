package org.bbstilson.raytracer.view

import org.bbstilson.raytracer.models._

import Math.pow

case class Material(
  color: Color,
  ambient: Double,
  diffuse: Double,
  specular: Double,
  shininess: Double
) {

  def light(
    light: PointLight,
    point: Point,
    eye: SceneVector,
    normal: SceneVector
  ): Color = {
    // Combine the surface color with the light's color/intensity.
    val effectiveColor = color * light.intensity

    // Find the direction to the light source.
    val lightv = (light.position - point).normalize

    // Compute the ambient contribution.
    val ambientComputed: Color = effectiveColor * ambient

    // lightDotNormal represents the cosine of the angle between the light
    // vector and the normal vector. A negative number means the light is on the
    // other side of the surface.

    val (diffuseComputed, specularComputed) = lightv.dot(normal) match {
      case lDN if lDN < 0 => (Color.BLACK, Color.BLACK)
      case lightDotNormal => {
        // Compute the diffuse contribution.
        val diffuseComputed = effectiveColor * diffuse * lightDotNormal

        // reflectDotEye represents the cosine of the angle between the reflection
        // vector and the eye vector. A negative number means the light reflects
        // away from the eye.
        val reflectV = SceneVector.reflect(-lightv, normal)
        val specularComputed = reflectV.dot(eye) match {
          case rDE if rDE <= 0 => Color.BLACK
          case reflectDotEye => {
            // Compute the specular contribution.
            val factor = pow(reflectDotEye, shininess)
            light.intensity * specular * factor
          }
        }

        (diffuseComputed, specularComputed)
      }
    }

    // Add the three contributions together to get the final shading
    ambientComputed + diffuseComputed + specularComputed
  }
}

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
    new Material(color, ambient, diffuse, specular, shininess)
  }
}
