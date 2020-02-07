fun main() {
    val code = getInput(5).split(",").map { it.trim().toBigInteger() }
    val (result, output) = intCodeExec(State(code, input = listOf(1.toBigInteger())))
    val (result2, output2) = intCodeExec(State(code, input = listOf(5.toBigInteger())))

    println("5.1:${output.dropLast(1).last()}")
    println("5.2:${output2.dropLast(1).last()}")
}