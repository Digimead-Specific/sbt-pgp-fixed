resolvers ++= Seq(
  Resolver.url("jgit-releases-for-sbt-pgp-build", url("http://download.eclipse.org/jgit/maven"))(Resolver.defaultIvyPatterns),
  Resolver.url("typesafe-ivy-releases-for-sbt-pgp-build", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns),
  Resolver.url("typesafe-ivy-snapshots-for-sbt-pgp-build", url("http://repo.typesafe.com/typesafe/ivy-snapshots/"))(Resolver.defaultIvyPatterns),
  Resolver.url("typesafe-repository-for-sbt-pgp-build", url("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/"))(Resolver.defaultIvyPatterns),
  Resolver.url("typesafe-shapshots-for-sbt-pgp-build", url("http://typesafe.artifactoryonline.com/typesafe/ivy-snapshots/"))(Resolver.defaultIvyPatterns),
  Resolver.url("sbt-plugin-releases-for-sbt-pgp-build", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns))
