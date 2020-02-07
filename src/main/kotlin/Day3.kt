import java.util.*
import kotlin.math.abs

fun main() {
    val input = processInput(getInput(3))
    println("3.1:${solve3task1(input)}")
    println("3.2:${solve3task2(input)}")
}

fun processInput(input: String): Pair<Wire, Wire> {
    val (w1, w2) = input
        .split("\n")
        .map {
            it.split(",")
                .filter { str -> str.isNotEmpty() }
                .map { str -> str.toInstruction }
        }
    return Pair(w1, w2)
}

fun solve3task1(wires: Pair<Wire, Wire>): Int =
    (mapWire(wires.first) intersect mapWire(wires.second))
        .filter { (it.x != 0) and (it.y != 0) }
        .map { (it mhd WPoint(0, 0)) }
        .min()!!


fun solve3task2(wires: Pair<Wire, Wire>): Int {
    val s1 = mapWire(wires.first)
    val s2 = mapWire(wires.second)
    val intersects = mapWire(wires.first) intersect mapWire(wires.second)
    return intersects
        .filter { (it.x != 0) and (it.y != 0) }
        .map { point ->
            s1.find { it eqCoordinate point }!!.travel + s2.find { it eqCoordinate point }!!.travel
        }
        .min()!!
}

fun mapWire(instr: Wire): SortedSet<WPoint> {
    var cp = WPoint(0, 0, 0)
    val set = sortedSetOf<WPoint>(compareBy({ it.y }, { it.x }))

    instr.forEach {
        for (i in 1..it.dist) {
            cp += WPoint(it.dir.dx, it.dir.dy, 1)
            set.add(cp)
        }
    }

    return set
}

val String.toInstruction: Instruction get() = Instruction(Direction.valueOf(take(1)), drop(1).toInt())

enum class Direction(val dx: Int, val dy: Int) { R(1, 0), U(0, -1), L(-1, 0), D(0, 1) }

data class Instruction(val dir: Direction, val dist: Int) {
    override fun toString(): String = "${this.dir}${this.dist}"
}

data class WPoint(val x: Int, val y: Int, val travel: Int = -1) {
    infix fun eqCoordinate(o: WPoint): Boolean = (x == o.x) and (y == o.y)
    infix fun mhd(o: WPoint): Int = abs(this.x - o.x) + abs(this.y - o.y)
    infix operator fun plus(o: WPoint): WPoint = WPoint(x + o.x, y + o.y, travel + o.travel)
}

typealias Wire = List<Instruction>