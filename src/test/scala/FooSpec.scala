package FooSpec

import Foo.Foo
import org.scalatest._

class FooSpec extends FlatSpec with Matchers {
  "addOne" should "add one" in {
    val foo = new Foo()
    foo.addOne(1) should be(2)
  }
}
