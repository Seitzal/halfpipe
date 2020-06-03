package halfpipe.Server

import halfpipe._
import cask._
import com.typesafe.scalalogging.LazyLogging
import com.typesafe.config.ConfigFactory
import java.io.File
import scala.io.Source

// Main definition of the backend server, including API routes.
object Server extends MainRoutes with LazyLogging {

  // Load the config file
  val config = ConfigFactory.parseFile(new File("config.json"))

  // Load client index
  def clientIndex = 
    Source.fromFile("client/index.html").getLines().mkString("\n")

  // Load client config
  def configJson =
    Source.fromFile("config.json").getLines().mkString("\n")

  // Serve the client index
  @cask.get("/")
  def index() = Response(
    data = clientIndex,
    statusCode = 200,
    headers = Seq("Content-type" -> "text/html"))

  // Serve the config to the client as javascript
  @cask.get("/config.js")
  def configJs() = Response(
    data = s"const config = $configJson",
    statusCode = 200,
    headers = Seq("Content-type" -> "text/javascript"))

  // Serve static files from client directory
  @cask.staticFiles("/static")
  def staticFileRoutes() = "client"

  // Helper method to add CORS headers to responses.
  def ok[T](content: T, cookies: Seq[Cookie] = Nil): Response[T] = 
    Response(content, 200, Seq("Access-Control-Allow-Origin" -> "*"), cookies)

  // Get the message of the day / MOTD.
  @get("/motd")
  def motd() = ok("Hello World!")

  // Cask needs us to do this.
  initialize()

}
