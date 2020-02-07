fun main() {
    val code = getInput(9).split(",").map { it.trim().toBigInteger() }
    val state = State(code, input = listOf(1.toBigInteger()))
    val res = intCodeExec(state)
    print("9.1:${res.output}")
}