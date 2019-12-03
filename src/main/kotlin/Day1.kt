fun main() {
    val input = getInput(1).map { it.toInt() }
    println("1.1:" + sumFuel(input))
    println("1.2:" + sumFuelRec(input))
}

fun calcFuel(v: Int): Int = v / 3 - 2

fun sumFuel(modules: List<Int>): Int = modules.map { calcFuel(it) }.sum()

fun calcFuelRec(mass: Int): Int {
    val fuel = calcFuel(mass)
    return if (fuel > 0) fuel + calcFuelRec(fuel) else 0
}

fun sumFuelRec(modules: List<Int>): Int = modules.map { calcFuelRec(it) }.sum()
