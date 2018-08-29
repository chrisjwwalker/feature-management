
package services

import javax.inject.Inject

class DefaultFeatureService @Inject()() extends FeatureService

trait FeatureService {

  private val BASE_KEY: String = "features"

  def getFeatureState(name: String): Boolean = {
    Option(System.getProperty(s"$BASE_KEY.$name")).fold(false)(_.toBoolean)
  }

  def setFeatureState(name: String, state: Boolean): Boolean = {
    System.setProperty(s"$BASE_KEY.$name", state.toString)
    state
  }
}
