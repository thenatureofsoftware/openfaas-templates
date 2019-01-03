package sample

import io.micronaut.runtime.Micronaut

object Fn {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("sample")
                .mainClass(Fn.javaClass)
                .start()
    }
}