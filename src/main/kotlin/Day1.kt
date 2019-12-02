fun main() {
    val input = getInput(1).map { it.toInt() }
    println("1.1:" + sumFuel(input))
    println("1.2:" + sumFuelRec(input))
}

// fun calcFuel(v: Int): Int = (floor(v / 3.0)).toInt() - 2
fun calcFuel(v: Int): Int = v / 3 - 2

fun sumFuel(modules: List<Int>): Int = modules.map { calcFuel(it) }.sum()

fun calcFuelRec(v: Int): Int {
    val f = calcFuel(v)
    return if (f > 0) f + calcFuelRec(f) else 0
}

fun sumFuelRec(modules: List<Int>): Int = modules.map { calcFuelRec(it) }.sum()
