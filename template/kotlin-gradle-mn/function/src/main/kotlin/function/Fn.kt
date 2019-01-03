package function

import io.micronaut.runtime.Micronaut

object Fn {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("function")
                .mainClass(Fn.javaClass)
                .start()
    }
}