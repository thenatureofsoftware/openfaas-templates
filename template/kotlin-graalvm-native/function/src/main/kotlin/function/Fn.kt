/*
 * OpenFaaS template function.
 */
package function

class Fn {

    fun hello(message: String): String {
        return """
            Hello from Kotlin GraalVM Native!
            Message: $message
        """.trimIndent()
    }
}

fun main(args: Array<String>) {
    println(Fn().hello(args.joinToString(separator = " ")))
}
