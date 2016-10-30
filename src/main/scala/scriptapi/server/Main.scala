package scriptapi.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.stream.ActorMaterializer
import akka.util.Timeout
import scriptapi.scriptloader.ActorCompiler

import scala.concurrent.duration.Duration

/**
  * Created by Jordi on 29-10-2016.
  */
object Main extends App {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher
    implicit val timeout: Timeout = Duration(300, scala.concurrent.duration.MILLISECONDS)

    val route =
      path("actor" / Segment) { segment =>
        get {
          complete((actor ? segment).map(_.asInstanceOf[String]))
        } ~
        post {
          // TODO create new actor based on payload
          complete(HttpResponse(StatusCodes.NotImplemented))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/")

    val props = ActorCompiler.getProps("GeneratedActor",
      s"""case "SayHello" => println(s"Hello world!")
         | case s: String =>
         |   val result = "Hello " + s
         |   println(s)
         |   sender() ! result
       """.stripMargin)
    val actor = system.actorOf(props)

    actor ! "SayHello"
  }
