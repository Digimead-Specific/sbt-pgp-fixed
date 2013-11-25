import sbt._
import Keys._

object SbtPgpBuild extends Build {
  override val settings: Seq[Setting[_]] =
    super.settings

  // Common Settings
  val commonSettings: Seq[Setting[_]] =
    Seq(
      licenses := Seq("BSD" -> url("http://www.opensource.org/licenses/bsd-license.php")),
      organization := "org.digimead",
      organizationHomepage := Some(url("http://digimead.org")),
      homepage := Some(url("https://github.com/digimead-specific/sbt-pgp")),
      scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-Xcheckinit", "-feature") ++
        (if (true || (System getProperty "java.runtime.version" startsWith "1.7")) Seq() else Seq("-optimize")), // -optimize fails with jdk7
      // http://vanillajava.blogspot.ru/2012/02/using-java-7-to-target-much-older-jvms.html
      javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation", "-source", "1.6", "-target", "1.6"),
      version := "0.8.2"
    ) ++ {
      if (sys.env.contains("XBOOTCLASSPATH"))
        Seq(javacOptions += "-Xbootclasspath:" + sys.env("XBOOTCLASSPATH"))
      else
        Seq()
    }

  // Dependencies
  val dispatchDependency: Setting[_] =
    libraryDependencies <+= (scalaVersion) apply { (sv) =>
      if(sv startsWith "2.9")       "net.databinder" % "dispatch-http_2.9.1" % "0.8.10"
      else                          "net.databinder" %% "dispatch-http" % "0.8.10"
    }
  val bouncyCastlePgp = "org.bouncycastle" % "bcpg-jdk15on" % "1.49"

  // Root project.  Just makes website and aggregates others.
  val root = (
    Project("sbtPGP", file("."))
    aggregate(plugin, library)
    settings(commonSettings:_*)
  )

  // The sbt plugin.
  lazy val plugin = Project("plugin", file("plugin")) dependsOn(library) settings(commonSettings:_*) settings(
    sbtPlugin := true,
    description := "The sbt-pgp plugin provides PGP signing",
    name := "sbt-pgp"
  ) settings(dispatchDependency)

  // The library of PGP functions.
  lazy val library = Project("library", file("library")) settings(commonSettings:_*) settings(
    name := "gpg-library",
    libraryDependencies += bouncyCastlePgp,
    dispatchDependency
  )
}
