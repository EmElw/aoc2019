fun intCodeExec(code: List<Int>, input: Int = 0): Pair<MutableList<Int>, MutableList<String>> {
    val list = code.toMutableList()
    val output = mutableListOf<String>()

    var pc = 0
    loop@ while (true) {
        val instr = list[pc].toString()
        pc++
        val op = instr.takeLast(2).toInt()
        val nOperands = op.numOperands()
        val operands = list.subList(pc, pc + nOperands)
        pc += operands.size
        val modes = instr.dropLast(2).padStart(nOperands, '0').reversed().map { it.toIntValue() }
        val values = operands.zip(modes).map { (op, mode) ->
            when (mode) {
                0 -> list[op]
                1 -> op
                else -> throw error("unrecognized parameter code $mode")
            }
        }

        when (op) {
            1 -> list[operands[2]] = values[0] + values[1]
            2 -> list[operands[2]] = values[0] * values[1]
            3 -> list[operands.first()] = input
            4 -> output.add(values.first().toString())
            5 -> if (values[0] != 0) pc = values[1]
            6 -> if (values[0] == 0) pc = values[1]
            7 -> list[operands[2]] = if (values[0] < values[1]) 1 else 0
            8 -> list[operands[2]] = if (values[0] == values[1]) 1 else 0
            99 -> {
                output.add("halted"); break@loop
            }
        }
    }
    return Pair(list, output)
}

private fun Char.toIntValue(): Int {
    if ((this >= '0') and (this <= '9')) return this.toInt() - '0'.toInt()
    else throw error("not a number")
}

private fun Int.numOperands(): Int = when (this) {
    99 -> 0
    1 -> 3  // add
    2 -> 3  // mul
    3 -> 1  // input
    4 -> 1  // output
    5 -> 2  // jump if true
    6 -> 2  // jump if false
    7 -> 3  // less than
    8 -> 3  // eq
    else -> throw error("unrecognized op code $this")
}

