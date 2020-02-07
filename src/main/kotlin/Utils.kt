/**
 * Returns a list containing all the elements in `this` and repeatedly appends [padding] such that size is equal to
 * [length]. The unaltered list is returned if size is greater than [length]
 */
public fun <T> List<T>.pad(length: Int, padding: T): List<T> {
    if (this.size >= length) return this
    return this.toMutableList().plus(List(length - this.size) { padding })
}

/**
 * Same as [pad] but mutates the list
 */
public fun <T> MutableList<T>.mutPad(length: Int, padding: T): List<T> {
    if (this.size >= length) return this
    this.addAll(List(length - this.size) { padding })
    return this
}

fun gcd(n1: Int, n2: Int): Int = if (n2 != 0) gcd(n2, n1 % n2) else n1
