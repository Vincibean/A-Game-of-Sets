name := "A Game of Sets"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Dependencies.deps

scalafmtOnCompile in ThisBuild := true
