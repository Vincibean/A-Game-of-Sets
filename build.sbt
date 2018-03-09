name := "A Game of Sets"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "org.specs2" %% "specs2-core" % "4.0.3" % Test,
  "org.specs2" %% "specs2-scalacheck" % "4.0.3" % Test
)

scalafmtOnCompile in ThisBuild := true
