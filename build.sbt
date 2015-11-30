name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.10",
  cache,
  javaWs
)

libraryDependencies += jdbc

libraryDependencies += "com.typesafe.play" % "play-java-jdbc_2.11" % "2.4.3"
libraryDependencies += "org.springframework" % "spring-jdbc" % "4.2.2.RELEASE"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.18"

libraryDependencies += "org.ocpsoft.prettytime" % "prettytime" % "1.0.8.Final"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

fork in run := true