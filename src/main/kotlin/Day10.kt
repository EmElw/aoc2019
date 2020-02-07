import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

// idea 1: raycast to each point on the edge -- didnt work since it can miss
// idea 2: pairwise check between all..?
//          -- can save work since the relation is reflexive

fun main() {
    val input = parseAsteroidField(getInput(10))
    val res1 = evalAsteroidField(input)
    val res2 = vaporizeAsteroids(input, res1.first, 200)
    println("10.1:${res1.second}")
    println("10.2:${res2.x * 100 + res2.y}")
}

data class Point(val x: Int, val y: Int) {
    override fun toString(): String = "($x,$y)"
    operator fun minus(o: Point) = Point(x - o.x, y - o.y)
    operator fun plus(o: Point) = Point(x + o.x, y + o.y)
}

fun parseAsteroidField(input: String): Set<Point> = input
    .split("\n")
    .mapIndexed { y, s ->
        s.mapIndexed { x, c -> if (c == '#') Point(x, y) else null }
    }
    .fold(mutableSetOf()) { acc, list ->
        acc.addAll(list.filterNotNull())
        acc
    }

fun evalAsteroidField(asteroids: Set<Point>): Pair<Point, Int> {
    val maxX = asteroids.maxBy { it.x }!!.x
    val maxY = asteroids.maxBy { it.y }!!.y

    return asteroids
        .map { it to asteroidsInView(asteroids, it, maxX, maxY).size }
        .maxBy { it.second }!!
}

fun asteroidsInView(a: Set<Point>, x: Int, y: Int, mx: Int, my: Int): List<Point> =
    asteroidsInView(a, Point(x, y), mx, my)

fun asteroidsInView(asteroids: Set<Point>, coord: Point, maxX: Int, maxY: Int): List<Point> {
    val (x, y) = coord
    val canSee = mutableSetOf<Point>()
    outer@ for (a in asteroids) {
        var (dx, dy) = a - coord
        if ((dx == 0) and (dy == 0)) continue
        val gcd = gcd(dx, dy);  // find smallest step size by dividing by gcd
        dy /= abs(gcd); dx /= abs(gcd)
        var nx = x
        var ny = y
        while ((nx in 0..maxX) and (ny in 0..maxY)) {   // step until out of bounds
            nx += dx; ny += dy
            if (asteroids.contains(Point(nx, ny))) {
                canSee.add(Point(nx, ny))
                continue@outer
            }
        }
    }
    return canSee.filter { it != coord }
}

fun vaporizeAsteroids(asteroidSet: Set<Point>, coord: Point, stopAt: Int): Point {
    val (x, y) = coord
    val asteroids = asteroidSet.toSortedSet(Comparator() { a, b ->
        laserAngle(coord, a).compareTo(laserAngle(coord, b))
    })

    if (stopAt > asteroids.size) throw error("Not enough asteroids to vaporize: $stopAt > ${asteroids.size}")

    val maxX = asteroids.maxBy { it.x }!!.x
    val maxY = asteroids.maxBy { it.y }!!.y
    var count = 0
    while (count < stopAt) {
        val canSee = asteroidsInView(asteroids, coord, maxX, maxY).sortedBy { laserAngle(coord, it) }
        for (a in canSee) {
            asteroids.remove(a)
            if (++count == stopAt) return a
        }
    }
    throw error("Unreachable")
}

fun laserAngle(p: Point, o: Point): Double {
    var (dx, dy) = p - o
    dy *= -1        // shift to standard math coordinates
    return (-atan2(dy.toDouble(), dx.toDouble()).plus(PI / 2)).plus(2 * PI).rem(2 * PI)
}


