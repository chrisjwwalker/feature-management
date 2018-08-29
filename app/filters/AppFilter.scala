
package filters

import akka.stream.Materializer
import javax.inject.Inject
import play.api.Logger
import play.api.mvc.{Filter, RequestHeader, Result}
import play.api.mvc.Results.ServiceUnavailable
import services.FeatureService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DefaultAppFilter @Inject()(implicit val mat: Materializer,
                                 val featureService: FeatureService) extends AppFilter {
  override def appEnabled: Boolean = featureService.getFeatureState("app")
}

trait AppFilter extends Filter {

  def appEnabled: Boolean

  def apply(f: RequestHeader => Future[Result])(rh: RequestHeader): Future[Result] = {
    if(appEnabled | rh.path.contains("/set-feature")) {
      Logger.info("App is enabled; proceeding")
      f(rh)
    } else {
      Logger.info("App is disabled; blocking")
      Future(ServiceUnavailable)
    }
  }
}