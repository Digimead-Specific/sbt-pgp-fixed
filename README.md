# PGP Plugin

This plugin aims to provide PGP signing for XSBT (SBT 0.12+ versions).  The plugin currently uses the command line GPG process with the option to use the Bouncy Castle java security library for PGP. 

# Modifications

Fixed version of sbt-pgp based on 0.9-SNAPSHOT. Support 0.11.x 0.12.x 0.13.x(SNAPSHOT) without build configuration modifications.

Just add to your Build.scala something like:

    import sbt._
    object PluginDef extends Build {
      override def projects = Seq(root)
      lazy val root = Project("plugins", file(".")) dependsOn(pgp)
      lazy val pgp = uri("git://github.com/ezh/sbt-pgp-fixed.git#0.99")
    }

and

    com.typesafe.sbt.SbtPgp.activate

to you project configuration. Now you may implement SBT crossbuild 0.11+ for you plugin easily ;-)

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
