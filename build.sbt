
val appName: String = "feature-management"

val service = Project("feature-management", file("."))
  .enablePlugins(PlayScala)
  .settings(
    name                                  := "feature-management",
    version                               := "1.0",
    resolvers                             += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    resolvers                             += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/",
    scalaVersion                          := "2.12.2",
    libraryDependencies                  ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice ),
    unmanagedResourceDirectories in Test <+= baseDirectory ( _ /"target/web/public/test" )
  )
      