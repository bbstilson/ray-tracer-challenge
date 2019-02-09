package org.bbstilson.raytracer.utils

import org.bbstilson.raytracer.UnitSpec

class MathUtilsSpec extends UnitSpec {
  behavior of "MathUtils"

  import MathUtils._

  "~=" should "evaluate approximately equal doubles" in {
    (0.3 ~= 0.30000004) should be(true)
  }
}
