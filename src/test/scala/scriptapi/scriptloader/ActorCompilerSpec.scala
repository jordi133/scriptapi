package scriptapi.scriptloader

import akka.actor.Props
import org.scalatest.WordSpec

/**
  * Created by Jordi on 30-10-2016.
  */
class ActorCompilerSpec extends WordSpec {

  "getProps" should {
    "return an object of type Props" in {
      val props = ActorCompiler.getProps("GeneratedActor", "case _ =>")
      assert(props.isInstanceOf[Props])
    }
  }
}
