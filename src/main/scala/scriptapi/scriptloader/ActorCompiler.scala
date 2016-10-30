package scriptapi.scriptloader

import akka.actor.Props

import scala.reflect.runtime.currentMirror
import scala.tools.reflect.ToolBox

/**
  * Created by Jordi on 29-10-2016.
  */
object ActorCompiler {
  def getProps(className: String, receiveBody: String) = {
    val code = createActorCode(className, receiveBody)
    createProps(code)
  }

  private def createProps(code: String): Props = {
    val toolbox = currentMirror.mkToolBox()
    val tree = toolbox.parse(code)
    val eval = toolbox.eval(tree)
    eval.asInstanceOf[Props]
  }

  private def createActorCode(className: String, receiveBody: String) = {
    s"""
       |import akka.actor.{Actor, Props}
       |
       |object $className {
       |  def props: Props = Props(new $className)
       |}
       |class $className extends Actor {
       |
       |  def receive: Receive = {
       |    $receiveBody
       |  }
       |}
       |
       |$className.props
       |""".stripMargin
  }
}
