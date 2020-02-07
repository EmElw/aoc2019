import java.math.BigInteger

fun main() {
    println(listOf(1).lastIndex)
    val input = getInput(7).split(",").map { it.trim().toBigInteger() }
    println("7.1:${findThrusterMax(input)}")
    println("7.2:${findThrusterMaxFeedback(input)}")
}

val THRUSTER_SETTINGS = (0..4).toList()
val THRUSTER_LOOP_SETTINGS = (5..9).toList()

fun findThrusterMax(code: List<BigInteger>): BigInteger {
    val permutations = nonexhaustivePermutations(5, THRUSTER_SETTINGS)
        .filter { it.containsAll(THRUSTER_SETTINGS) }
    return permutations
        .map {
            it.fold(0.toBigInteger(), { out, inp -> evalThruster(code, inp.toBigInteger(), out) })
        }
        .max()!!
}

fun findThrusterMaxFeedback(code: List<BigInteger>): Pair<BigInteger, List<BigInteger>>? {
    val permutations = nonexhaustivePermutations(5, THRUSTER_LOOP_SETTINGS)
        .filter { it.containsAll(THRUSTER_LOOP_SETTINGS) }
    return permutations.map { feedbackEval(code, it.map(Int::toBigInteger)) }.maxBy { it.first }
}

fun feedbackEval(code: List<BigInteger>, settings: List<BigInteger>): Pair<BigInteger, List<BigInteger>> {
    val states = MutableList(5) { State(code, listOf(settings[it])) }
    val n = states.size
    states[0] = states[0].addInput(0.toBigInteger())   // starting signal

    while (true)
        for (i in 0 until n) {
            val j = (i + 1) % n   // next index
            val (newState, out) = intCodeExec(states[i]).readOutput()
            states[i] = newState
            states[j] = states[j].addInput(out)
            if ((i == states.lastIndex) and (newState.halted)) {
                return Pair(out.last(), settings)
            }
        }
}


fun evalThruster(code: List<BigInteger>, setting: BigInteger, input: BigInteger): BigInteger {
    val newCode = code.toMutableList()
    val output = intCodeExec(newCode, listOf(setting, input)).output
    return output[0]
}

fun <T> nonexhaustivePermutations(length: Int, components: List<T>): List<List<T>> =
    if (components.isEmpty() || length <= 0) listOf(listOf())
    else nonexhaustivePermutations(length - 1, components).flatMap { sub ->
        components.map { elm -> sub + elm }
    }