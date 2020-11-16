package bug.web.rest

import org.eclipse.microprofile.openapi.annotations.Operation
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path

@ApplicationScoped
@Path("dummy")
class DummyResource {
    @GET
    @Operation(operationId = "dummy-operation", summary = "Test if OpenApi is working")
    fun hello() = "Hello"

    @Path("/this-endpoint-cause-issue")
    @PUT
    fun updateSomething(requestBody: RequestBody) {
        // issue would be occurs when the request body model contains an Enum field
    }
}

class RequestBody {
    var value: AnyEnum? = null
}

enum class AnyEnum {
    A, B, C
}
