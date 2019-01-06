/*
 * OpenFaaS template function.
 */
package function

class Fn {

    fun hello(message: String): String {
        return """
            Hello from Kotlin GraalVM Native!
            Message: $message

        """.trimIndent() + System.getenv().entries.map { "${it.key}=${it.value}" }.joinToString(separator = "\n")
    }
}

fun main(args: Array<String>) {
    val lines = mutableListOf<String>()
    while(System.`in`.available() > 0) {
        lines.add(readLine() ?: "")
    }
    println(Fn().hello(lines.joinToString(separator = "\n")))
}
