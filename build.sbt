name := "A Game of Sets"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.typelevel" %% "cats-core" % "1.0.1",
  "com.beachape" %% "enumeratum" % "1.5.12",
  "eu.timepit" %% "refined" % "0.8.7",
  "org.specs2" %% "specs2-core" % "4.0.3" % Test,
  "org.specs2" %% "specs2-scalacheck" % "4.0.3" % Test,
  "eu.timepit" %% "refined-scalacheck" % "0.8.7" % Test
)

scalafmtOnCompile in ThisBuild := true
