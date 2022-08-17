val scala3Version = "3.1.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "cats",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )

libraryDependencies += "org.typelevel" %% "cats-core" % "2.8.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % Test
scalacOptions += "-language:higherKinds"