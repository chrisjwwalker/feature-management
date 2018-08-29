import controllers.{DefaultFeatureController, DefaultSampleController, FeatureController, SampleController}
import filters.{AppFilter, DefaultAppFilter}
import play.api.{Configuration, Environment}
import play.api.inject.{Binding, Module}
import services.{DefaultFeatureService, FeatureService}

class Bindings extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[_]] =
    bindServices() ++ bindFilters() ++ bindControllers ++ bindStartup()

  private def bindServices(): Seq[Binding[_]] = Seq(
    bind(classOf[FeatureService]).to(classOf[DefaultFeatureService]).eagerly()
  )

  private def bindFilters(): Seq[Binding[_]] = Seq(
    bind(classOf[AppFilter]).to(classOf[DefaultAppFilter]).eagerly()
  )

  private def bindControllers: Seq[Binding[_]] = Seq(
    bind(classOf[FeatureController]).to(classOf[DefaultFeatureController]).eagerly(),
    bind(classOf[SampleController]).to(classOf[DefaultSampleController]).eagerly()
  )

  private def bindStartup(): Seq[Binding[_]] = Seq(
    bind(classOf[Startup]).toSelf
  )
}
