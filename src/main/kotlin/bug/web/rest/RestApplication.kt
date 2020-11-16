package bug.web.rest

import bug.web.rest.RestApplication.Companion.API_PATH
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath(API_PATH)
class RestApplication : Application() {
    companion object {
        const val API_PATH = "api"
    }
}