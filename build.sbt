import sbt.Project.projectToRef

name := "scriptapi"
version := "1.0"

lazy val scalaV = "2.11.8"
lazy val scalaTestV = "3.0.0-M15"
lazy val akkaV = "2.4.11"

scalaVersion := scalaV

resolvers += "Twitter repo" at "http://maven.twttr.com/"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % "test",
  "junit" % "junit" % "4.8.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.6",
  "com.typesafe.akka" %% "akka-actor" % akkaV,
  "com.typesafe.akka" %% "akka-http-core" % akkaV,
  "com.typesafe.akka" %% "akka-http-experimental" % akkaV,
  "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaV % "test",
  "com.twitter" %% "util-eval" % "6.38.0",
  "org.scala-lang" % "scala-compiler" % scalaVersion.value
)


