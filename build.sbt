import VersionKeys._

scalaModuleSettings

name                       := "scala-partest"

version                    := "1.0.10-fields"

scalaVersion               := "2.12.0-fields"

scalaHome := Some(file("/Users/adriaan/git/scala/build/pack"))

scalaXmlVersion            := "1.0.5"

scalaCheckVersion          := "1.11.6"

// TODO: eliminate "-deprecation:false" for nightlies,
//   included by default because we don't want to break scala/scala pr validation
scalacOptions ++=
  Seq("-Ydebug", "-feature", "-deprecation:false", "-unchecked", "-Xlint") ++
  (CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor < 12 =>
      Seq("-optimize")
    case _ =>
      Seq()  // maybe "-Yopt:l:classpath" eventually?
  })

// dependencies
// versions involved in integration builds / that change frequently should be keys, set above!
libraryDependencies += "org.apache.ant"                 % "ant"            % "1.8.4" % "provided"

libraryDependencies += "com.googlecode.java-diff-utils" % "diffutils"      % "1.3.0"

libraryDependencies += "org.scala-sbt"                  % "test-interface" % "1.0"

// to run scalacheck tests, depend on scalacheck separately
libraryDependencies += "org.scalacheck"                 % "scalacheck_2.12.0-M3"     % scalaCheckVersion.value % "provided"

// mark all scala dependencies as provided which means one has to explicitly provide them when depending on partest
// this allows for easy testing of modules (like scala-xml) that provide tested classes themselves and shouldn't
// pull in an older version of itself
libraryDependencies += "org.scala-lang.modules"         % "scala-xml_2.12.0-M3"      % scalaXmlVersion.value % "provided" intransitive()

libraryDependencies += "org.scala-lang"                 % "scalap"         % scalaVersion.value % "provided" intransitive()

libraryDependencies += "org.scala-lang"                 % "scala-reflect"  % scalaVersion.value % "provided" intransitive()

libraryDependencies += "org.scala-lang"                 % "scala-compiler" % scalaVersion.value % "provided" intransitive()

mimaPreviousVersion := Some("1.0.5")
