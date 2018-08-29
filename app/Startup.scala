import features.Features
import javax.inject.Inject
import play.api.Logger
import services.FeatureService

class Startup @Inject()(val featureService: FeatureService) {

  private val switch: Boolean => String = boolean => if(boolean) "ENABLED" else "DISABLED"

  Features.features foreach { feature =>
    val states = Map(feature -> featureService.getFeatureState(feature))
    states.foreach { case (featureName, state) =>
      Logger.info(s"Feature $featureName is currently ${switch(state)}")
    }
  }
}
