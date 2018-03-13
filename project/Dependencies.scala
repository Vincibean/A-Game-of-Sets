import sbt._

object Dependencies {

  private val specs2Version = "4.0.3"
  private val refinedVersion = "0.8.7"

  val deps = Seq(
    "com.chuusai" %% "shapeless" % "2.3.3",
    "org.typelevel" %% "cats-core" % "1.0.1",
    "com.beachape" %% "enumeratum" % "1.5.12",
    "eu.timepit" %% "refined" % refinedVersion,
    "org.specs2" %% "specs2-core" % specs2Version % Test,
    "org.specs2" %% "specs2-scalacheck" % specs2Version % Test,
    "eu.timepit" %% "refined-scalacheck" % refinedVersion % Test
  )

}
