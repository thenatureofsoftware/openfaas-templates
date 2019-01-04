package function

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Fn

fun main(args: Array<String>) {
	runApplication<Fn>(*args)
}

