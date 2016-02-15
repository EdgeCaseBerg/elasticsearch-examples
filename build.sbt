import scalariform.formatter.preferences._

organization := "com.github.edgecaseberg"

name := "elastic-examples"
 
version := "0.0.1-SNAPSHOT"
 
scalaVersion := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

libraryDependencies ++= Seq(
		"org.elasticsearch" % "elasticsearch" % "2.2.0",
		"org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
	)

scalariformPreferences := scalariformPreferences.value
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)
  .setPreference(AlignParameters, false)
  .setPreference(IndentWithTabs, true)
  .setPreference(MultilineScaladocCommentsStartOnFirstLine, true)

fork in Test := true