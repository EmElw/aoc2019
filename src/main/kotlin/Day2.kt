const val TASK2_GOAL = 19690720

fun main() {
    val input =
        getInput(2, defaultSessionToken())
            .split(",")
            .map { it.trim().toInt() }
            .toList()
    val mutInt = input.toMutableList()
    mutInt[1] = 12
    mutInt[2] = 2
    val (result, _) = intCodeExec(mutInt)
    println("2.1:${result[0]}")
    val res = intCodeBruteForce(input, TASK2_GOAL)
    println("2.2:${res.first * 100 + res.second} -- $res")
}


fun intCodeBruteForce(input: List<Int>, goal: Int, range: Int = 99): Pair<Int, Int> {
    for (n in 0..range) for (v in 0..range) {
        val newList = input.toMutableList()
        newList[1] = n
        newList[2] = v
        if (intCodeExec(newList).first[0] == goal) return Pair(n, v)
    }
    throw error("No solution found in range 0-$range")
}
