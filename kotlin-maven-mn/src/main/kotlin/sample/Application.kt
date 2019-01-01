package sample

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("sample")
                .mainClass(Application.javaClass)
                .start()
    }
}