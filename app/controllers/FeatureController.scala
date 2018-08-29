
package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.FeatureService

import scala.util.{Failure, Success, Try}

class DefaultFeatureController @Inject()(val featureService: FeatureService,
                                         val controllerComponents: ControllerComponents) extends FeatureController

trait FeatureController extends BaseController {
  val featureService: FeatureService

  def setState(name: String, state: String): Action[AnyContent] = Action { implicit request =>
    Try(featureService.setFeatureState(name, state.toBoolean)) match {
      case Success(_) => Ok
      case Failure(_) => InternalServerError
    }
  }
}
