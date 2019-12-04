fun main() {
    val (lo, hi) = getInput(4).split("-").map { it.trim().toInt() }
    println("4.1:${(lo..hi).map { it.toString() }.filter { criteria(it) }.size}")
    println("4.2:${(lo..hi).map { it.toString() }.filter { criteria2(it) }.size}")
}

fun criteria(s: String): Boolean = all(
    s.zipWithNext().none { (a, b) -> b < a },
    s.zipWithNext().any { (a, b) -> a == b }
)

fun criteria2(s: String): Boolean = all(
    s.zipWithNext().none { (a, b) -> b < a },
    ('0'..'9').any { s.indexOfLast { c -> c == it } - s.indexOfFirst { c -> c == it } == 1 }
)

fun all(vararg conditions: Boolean) = conditions.all { it }