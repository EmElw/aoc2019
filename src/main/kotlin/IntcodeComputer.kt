import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.pow

const val HALTED = "halted"


fun intCodeExec(code: List<Int>) = intCodeExec(code.map(Int::toBigInteger))

fun intCodeExec(
    code: List<BigInteger>,
    input: List<BigInteger> = emptyList(),
    output: List<BigInteger> = emptyList(),
    pc: Int = 0,
    halted: Boolean = false
) = intCodeExec(State(code, input, output, pc, halted))

fun intCodeExec(
    state: State
): State {
    val codeMem = state.code.toMutableList()
    val output = state.output.toMutableList()
    val input = state.input.toMutableList()
    var pc = state.pc
    var relPos = state.relPos

    loop@ while (true) {
        val instr = codeMem[pc].toDigits()
        val op = instr.take(2).toInt()
        val nOps = op.numOperands()
        val operands = codeMem.subList(pc + 1, pc + nOps + 1).toList()
        val modes = instr.drop(2).pad(nOps, 0)
        val opValues = operands // values adjusted for mode
            .zip(modes)
            .map { (op, mode) ->
                val iOp = op.toInt()
                when (mode) {
                    0 -> {
                        if (codeMem.size < iOp) codeMem.mutPad(iOp + 10, 0.toBigInteger())
                        codeMem[op.toInt()]
                    }
                    1 -> op
                    2 -> {
                        if (codeMem.size < relPos - iOp) codeMem.mutPad(relPos - iOp + 10, 0.toBigInteger())
                        codeMem[relPos - iOp]
                    }
                    else -> throw error("unrecognized parameter code $mode")
                }
            }

        when (op) {
            1 -> codeMem[operands[2].toInt()] = opValues[0] + opValues[1]
            2 -> codeMem[operands[2].toInt()] = opValues[0] * opValues[1]
            3 -> {
                if (input.isEmpty()) return State(codeMem, input, output, pc)
                else codeMem[operands.first().toInt()] = input.removeAt(0)
            }
            4 -> output.add(opValues.first())
            5 -> if (opValues[0].toInt() != 0) {
                pc = opValues[1].toInt()
                continue@loop   // skip normal increment pc
            }
            6 -> if (opValues[0].toInt() == 0) {
                pc = opValues[1].toInt()
                continue@loop   // skip normal increment pc
            }
            7 -> codeMem[operands[2].toInt()] = (if (opValues[0] < opValues[1]) 1 else 0).toBigInteger()
            8 -> codeMem[operands[2].toInt()] = (if (opValues[0] == opValues[1]) 1 else 0).toBigInteger()
            9 -> relPos += opValues[0].toInt()
            99 -> {
                return State(codeMem, input, output, pc, halted = true)
            }
        }
        pc += operands.size + 1
    }
}


private fun BigInteger.toDigits(): List<Int> {
    val l = mutableListOf<Int>()
    var a: Int = this.abs().intValueExact()
    while (a > 9) {
        l.add(a.rem(10))
        a /= 10
    }
    l.add(a)
    return l
}

private fun List<Int>.toInt(): Int = this.foldIndexed(0, { i, acc, v -> acc + v * (10.0.pow(i).toInt()) })

data class State(
    val code: List<BigInteger>,
    val input: List<BigInteger> = emptyList(),
    val output: List<BigInteger> = emptyList(),
    val pc: Int = 0,
    val halted: Boolean = false,
    val relPos: Int = 0
) {
    fun addInput(vararg int: BigInteger): State {
        val newInput = this.input.toMutableList()
        newInput.addAll(int.toList())
        return copy(input = newInput)
    }

    fun addInput(input: List<BigInteger>): State {
        val newInput = this.input.toMutableList()
        newInput.addAll(input)
        return copy(input = newInput)
    }

    fun readOutput(): Pair<State, List<BigInteger>> = Pair(copy(output = emptyList()), output)
}

private fun Int.numOperands(): Int = when (this) {
    99 -> 0
    1 -> 3  // add
    2 -> 3  // mul
    3 -> 1  // input
    4 -> 1  // output
    5 -> 2  // jump to op2 if op1 true
    6 -> 2  // jump to op2 if op1 false
    7 -> 3  // less than (if a < b then c = 1 else c = 0)
    8 -> 3  // eq        (if a = b then c = 1 else c = 0)
    9 -> 1  // add to relative position
    else -> throw error("unrecognized op code $this")
}