fun main() {
    val code = getInput(5).split(",").map { it.trim().toInt() }
    val (result, output) = intCodeExec(code = code, input = 1)
    val (result2, output2) = intCodeExec(code = code, input = 5)

    println("5.1:${output.dropLast(1).last()}")
    println("5.2:${output2.dropLast(1).last()}")
}