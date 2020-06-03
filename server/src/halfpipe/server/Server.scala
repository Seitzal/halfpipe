package halfpipe.Server

import halfpipe._
import cask._
import com.typesafe.scalalogging.LazyLogging

// Main definition of the backend server, including API routes.
object Server extends MainRoutes with LazyLogging {

  // Helper method to add CORS headers to responses.
  def ok[T](content: T, cookies: Seq[Cookie] = Nil): Response[T] = 
    Response(content, 200, Seq("Access-Control-Allow-Origin" -> "*"), cookies)

  // Endpoint to get the message of the day / MOTD.
  @get("/motd")
  def motd() = ok("Hello World!")

  // Cask needs us to do this.
  initialize()

}
