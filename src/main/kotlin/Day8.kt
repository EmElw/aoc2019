fun main() {
    val input = getInput(8).trim()
    println(input.length % (25 * 6))
    val image = parseSIF(input, 25, 6)

    println("8.1:${image.minBy { it.countDigit(0) }?.run { countDigit(1) * countDigit(2) }}")

    println("8.2:\n")
    decodeSIF(image).string.chunked(25).forEach {
        println(it.map {
            when (it) {
                WHITE -> '█'
                BLACK -> ' '
                TRANSPARENT -> ' '
                else -> error("unrecognized color $it")
            }
        }.fold("", { acc, c -> acc + c }))
    }
}

fun parseSIF(string: String, width: Int = 25, height: Int = 5): List<Layer> {
    if (string.length % (25 * 6) != 0) throw error("string of bad len: ${string.length}")
    return string.chunked(width * height).map { Layer(it, width) }
}

fun decodeSIF(layers: List<Layer>): Layer {
    val string = layers[0].string.toMutableList()
    layers.forEach {
        it.string.forEachIndexed { i, c ->
            if (string[i] == TRANSPARENT) string[i] = c
        }
    }
    return Layer(string.fold("", { acc, c -> acc + c }), layers[0].width)
}


data class Layer(val string: String, val width: Int) {
    fun countDigit(digit: Int) = string.filter { it.toString() == digit.toString() }.length
}

const val BLACK = '0'
const val WHITE = '1'
const val TRANSPARENT = '2'
