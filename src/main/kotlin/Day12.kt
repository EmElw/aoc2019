import kotlin.math.abs
import kotlin.math.sign

data class Moon(
    val p: List<Int>,
    val v: List<Int> = listOf(0, 0, 0)
) {
    fun applyVelocity(): Moon = copy(p = p.zip(v).map { (p, d) -> p + d })
    fun applyGravity(others: List<Moon>): Moon {
        var dX = 0
        var dY = 0
        var dZ = 0
        for (other in others) {
            val (oX, oY, oZ) = other.p
            dX += oX.compareTo(p[0]).sign
            dY += oY.compareTo(p[1]).sign
            dZ += oZ.compareTo(p[2]).sign
        }
        return copy(v = listOf(v[0] + dX, v[1] + dY, v[2] + dZ))
    }

    fun axisEquality(axis: Int, other: Moon): Boolean {
        return p[axis] == other.p[axis] && v[axis] == other.v[axis]
    }
}


fun main() {
    val moons = getInput(12).split("\n").filter { it != "" }.map { parseMoon(it.trim()) }
    val moonsAfter1000 = simulate(moons, 1000)
    println("12.1:${calcSystemEnergy(moonsAfter1000)}")
    println("12.2:${findRepeatingState(moons)}")
}

fun parseMoon(input: String): Moon {
    val values = input.split(",").map { it.filter { c -> c.isDigit() || c == '-' }.toInt() }
    if (values.size == 3) return Moon(values)
    if (values.size == 6) return Moon(values.subList(0, 3), values.subList(3, 6))
    throw error("Moon parse error: ($input) $values")
}

fun simulate(inp: List<Moon>, steps: Int): List<Moon> {
    var moons = inp
    for (step in 0 until steps) {
        moons = moons.map { it.applyGravity(moons) }.map { it.applyVelocity() }
    }
    return moons
}

fun calcSystemEnergy(moons: List<Moon>): Int =
    moons.map { moon -> moon.v.map { abs(it) }.sum() * moon.p.map { abs(it) }.sum() }.sum()

fun findRepeatingState(moons: List<Moon>): Int {
    val axis = moons.first().p.size
    //
    val list = mutableListOf<Int>()
    for (i in 0 until axis) {
        list.add(findAxisPeriod(i, moons))
    }

    return list.reduce { acc, v ->
        if (gcd(acc, v) == 1) acc * v else gcd(acc, v)
    }
}

/**
 * Given a list of moons, returns the number of iterations needed to reach the initial
 * state of the [axisIndex]:th axis
 */
fun findAxisPeriod(axisIndex: Int, initial: List<Moon>): Int {
    var moons = simulate(initial, 1)
    var count = 0

    while (!initial.zip(moons).all { (lh, rh) -> lh.axisEquality(axisIndex, rh) }) {
        moons = simulate(moons, 1)
        count++
    }

    return count
}
