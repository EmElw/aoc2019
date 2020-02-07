import java.math.BigInteger

const val TASK2_GOAL = 19690720

fun main() {
    val input = getInput(2)
        .split(",")
        .map { it.trim().toBigInteger() }
        .toList()
    val mutInt = input.toMutableList()
    mutInt[1] = 12.toBigInteger()
    mutInt[2] = 2.toBigInteger()
    val (result, _) = intCodeExec(mutInt.map { it })
    println("2.1:${result[0]}")
    val res = intCodeBruteForce(input, TASK2_GOAL)
    println("2.2:${res.first * 100 + res.second} -- $res")
}


fun intCodeBruteForce(input: List<BigInteger>, goal: Int, range: Int = 99): Pair<Int, Int> {
    for (n in 0..range) for (v in 0..range) {
        val newList = input.toMutableList()
        newList[1] = n.toBigInteger()
        newList[2] = v.toBigInteger()
        if (intCodeExec(newList.map { it }).output[0].toInt() == goal) return Pair(n, v)
    }
    throw error("No solution found in range 0-$range")
}
