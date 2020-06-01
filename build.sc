import mill._, scalalib._

object server extends ScalaModule {
  def scalaVersion = "2.13.2"
  def ivyDeps = Agg(
    ivy"ch.qos.logback:logback-classic:1.2.3",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.2",
    ivy"com.lihaoyi::upickle:0.9.5",
    ivy"com.lihaoyi::cask:0.6.7"
  )
}