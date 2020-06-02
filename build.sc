import mill._, scalalib._

object core extends JavaModule {
  object test extends Tests {
    def ivyDeps = Agg(ivy"com.novocode:junit-interface:0.11")
    def testFrameworks = Seq("com.novocode.junit.JUnitFramework")
  }
}

object server extends ScalaModule {
  def scalaVersion = "2.13.2"
  def moduleDeps = Seq(core)
  def ivyDeps = Agg(
    ivy"ch.qos.logback:logback-classic:1.2.3",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.2",
    ivy"com.lihaoyi::upickle:0.9.5",
    ivy"com.lihaoyi::cask:0.6.7"
  )

  object test extends Tests {
    def ivyDeps = Agg(ivy"org.scalatest::scalatest:3.1.2")
    def testFrameworks = Seq("org.scalatest.tools.Framework")
  }
}