
package filters

import javax.inject.Inject
import play.api.http.DefaultHttpFilters

class Filters @Inject()(appFilter: AppFilter) extends DefaultHttpFilters(appFilter)
