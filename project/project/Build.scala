import sbt._
object PluginDef extends Build {
  override def projects = Seq(root)
  lazy val root = Project("plugins", file(".")) dependsOn(dm)
  lazy val dm = uri("git://github.com/sbt-android-mill/sbt-dependency-manager.git#cc64a0ca4f481083745216240735f1c1ac6780d6")
}
