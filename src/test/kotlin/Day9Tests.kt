import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day9Tests {
    @Test
    fun `Small examples 1`() {

        val quine =
            listOf(109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99).map(Int::toBigInteger)
        val res = intCodeExec(quine)

        val sixteenNum = listOf(1102, 34915192, 34915192, 7, 4, 7, 99, 0).map(Int::toBigInteger)
        val res2 = intCodeExec(sixteenNum)
        Assertions.assertEquals(16, res2.output.toString().filterNot { "[]".contains(it) }.length)
//        Assertions.assertEquals(quine,res.output.subList(2,outEnd))
        val bigNum = listOf(104.toBigInteger(), 1125899906842624.toBigInteger(), 99.toBigInteger())
        val res3 = intCodeExec(bigNum)
        Assertions.assertEquals(res3.output.first(), (bigNum[1]))
    }

}