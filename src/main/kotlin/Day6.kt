fun main() {
    val input = getInput(6)
    println("6.1:${checkSumOrbits(input)}")
    println("6.2:${orbitalDistance(input, "YOU", "SAN")}")
}

fun parse(input: String) = input
    .split("\n")
    .filter { it.isNotEmpty() }
    .map {
        val (o, oe) = it.split(")")
        Pair(oe, o)
    }
    .toMutableSet()

fun checkSumOrbits(input: String): Int {
    val map = parse(input).toMap()
    return map.keys.sumBy {
        var sum = 0
        var current = it
        while (map.containsKey(current)) {
            sum++
            current = map[current]!!
        }
        sum
    }
}

fun orbitalDistance(input: String, from: String = "YOU", to: String = "SAN"): Int {
    val map = parse(input).toMap()
    if (!map.containsKey(from)) error("Missing $from in input")
    if (!map.containsKey(to)) error("Missing $to in input")

    val pathToRoot = mutableSetOf<Pair<String, Int>>()
    var currentFrom = from
    var distFrom = 0
    while (map.containsKey(currentFrom)) {
        currentFrom = map[currentFrom]!!
        pathToRoot.add(currentFrom to distFrom++)
    }
    var currentTo = to
    var distTo = -1
    while (map.containsKey(currentTo)) {
        val o = pathToRoot.find { it.first == currentTo }
        if (o != null) return distTo + o.second
        currentTo = map[currentTo]!!
        distTo++
    }
    throw error("Could not find path between $from and $to")
}

