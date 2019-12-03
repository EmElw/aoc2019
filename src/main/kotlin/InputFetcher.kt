import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import java.io.File
import java.io.InputStream
import java.lang.Exception

const val R_PATH = "src/main/resources/"

// fetches all available inputs
fun main() {
    for (n in 1..24) {
        try {
            getInput(n)
        } catch (e: Exception) {
            println("Aborting after $n days")
            break
        }
    }
}

fun defaultSessionToken() = File("${R_PATH}session.txt")
    .inputStream()
    .bufferedReader()
    .use { it.readText() }

fun getInput(
    day: Int, session:
    String = defaultSessionToken()
): String {
    val cookies = mapOf("session" to session)

    val file = File("${R_PATH}input$day")
    if (file.exists()) {
        return file
            .inputStream()
            .bufferedReader()
            .use { it.readText() }
    }

    println("Fetching input for day $day")

    val (_, _, result) =
        "https://adventofcode.com/2019/day/$day/input"
            .httpGet()
            .header(Headers.COOKIE to "session=$session")
            .responseString()

    if (result is Result.Failure) {
        println(" -- failed")
        throw result.getException()
    }

    file.writeText(result.get())

    println(" -- success")
    return result.get()
}