package function

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class Handler {

    @PostMapping(produces = [MediaType.TEXT_PLAIN_VALUE])
    fun handle(@RequestBody message: String): String {
        return """
            Hello World from Kotlin Spring Boot!
            Message: $message

        """.trimIndent()
    }

}