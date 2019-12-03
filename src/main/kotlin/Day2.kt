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
    val result = intCodeExec(mutInt)
    println("2.1:${result[0]}")
    val res = intCodeBruteForce(input, TASK2_GOAL)
    println("2.2:${res.first * 100 + res.second} -- $res")
}

fun intCodeExec(input: List<Int>): List<Int> {
    val list = input.toMutableList()
    var pc = 0

    while (list[pc] != 99) {
        val op1 = list[list[pc + 1]]
        val op2 = list[list[pc + 2]]
        val pos = list[pc + 3]
        when (list[pc]) {
            1 -> list[pos] = op1 + op2
            2 -> list[pos] = op1 * op2
            else -> throw error("Unrecognized op code ${list[pc]}")
        }
        pc += 4
    }
    return list
}

fun intCodeBruteForce(input: List<Int>, goal: Int, range: Int = 99): Pair<Int, Int> {
    for (n in 0..range) for (v in 0..range) {
        val newList = input.toMutableList()
        newList[1] = n
        newList[2] = v
        if (intCodeExec(newList)[0] == goal) return Pair(n, v)
    }
    throw error("No solution found in range 0-$range")
}
