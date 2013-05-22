# PGP Plugin

This plugin aims to provide PGP signing for XSBT (SBT 0.12+ versions).  The plugin currently uses the command line GPG process with the option to use the Bouncy Castle java security library for PGP. 

# Modifications

Fixed version of sbt-pgp based on 0.9-SNAPSHOT. Support 0.11.x 0.12.x 0.13.x(SNAPSHOT) without build configuration modifications.

Just add to your Build.scala something like:

    import sbt._
    object PluginDef extends Build {
      override def projects = Seq(root)
      lazy val root = Project("plugins", file(".")) dependsOn(pgp)
      lazy val pgp = uri("git://github.com/ezh/sbt-pgp-fixed.git")
    }

and

    com.typesafe.sbt.SbtPgp.activate

to you project configuration. Now you may implement SBT crossbuild 0.11+ for you plugin easily ;-)

P.S. 0.13 worked only with local installation(publish-local). When I tried to use web install I got:

    [info] Compiling 1 Scala source to /home/ezh/.sbt/0.13.0-20130520-052156/staging/7ea980335232cf0370da/project/project/target/scala-2.10/sbt-0.13.0-20130520-052156/classes...
    [error] Invalid project ID: Expected letter
    [error] 0-13-0-20130520-052156-build-build-build
    [error] ^
    [error] Use 'last' for the full log.
    
IMHO this is pure SBT feature that hasn't any relation with plugin source code. I hope that it will be fixed with release or not.

Best regards, Ezh.

## Usage

**WARNING** The PGP plugin as of 0.8 *NO LONGER* signs artifacts using the `publish` and `publish-local` task.  To sign artifacts, please use `publish-signed` and `publish-local-signed` tasks.



If you already have GPG configured, simply add the following to your `~/.sbt/plugins/gpg.sbt` file:

    resolvers += Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)
    
    addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8")


If you're using SBT 0.11.3 or earlier, please find the PGP plugin at its previous location:

    resolvers += Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)
 
    addSbtPlugin("com.jsuereth" % "xsbt-gpg-plugin" % "0.6")

The plugin should wire into all your projects and sign files before they are deployed when using the `publish-signed` task.

No other configuration should be necessary if you have a `gpg` generated key available.

Please see the [documentation](http://scala-sbt.org/sbt-pgp) for more information on usage.
