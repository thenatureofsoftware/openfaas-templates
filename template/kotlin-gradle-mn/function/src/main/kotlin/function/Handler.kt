package function

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post


@Controller("/")
class Handler {

    @Consumes(MediaType.TEXT_PLAIN)
    @Post(produces = [MediaType.TEXT_PLAIN])
    fun index(@Body() msg: String): String {
        return """
            Hello World from Kotlin Micronaut!
            Message: $msg

        """.trimIndent()
    }
}