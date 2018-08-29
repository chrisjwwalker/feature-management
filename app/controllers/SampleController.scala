
package controllers

import features.Features
import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.FeatureService
import views.html.SampleView

class DefaultSampleController @Inject()(val controllerComponents: ControllerComponents,
                                        val featureService: FeatureService) extends SampleController {
  override def contactDetailsEnabled: Boolean = featureService.getFeatureState(Features.contactDetails)
  override def loginEnabled: Boolean          = featureService.getFeatureState(Features.login)
}

trait SampleController extends BaseController {

  //Features
  def contactDetailsEnabled: Boolean
  def loginEnabled: Boolean

  //Controller Actions
  def show(): Action[AnyContent] = Action {
    Ok(SampleView(contactDetailsEnabled, loginEnabled))
  }
}
