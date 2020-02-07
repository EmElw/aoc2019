import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day2Tests {
    @Test
    fun `Day 2 task 1`() {
        val assertions = listOf( // input -> expected
            listOf(1, 0, 0, 0, 99) to listOf(2, 0, 0, 0, 99),
            listOf(2, 3, 0, 3, 99) to listOf(2, 3, 0, 6, 99),
            listOf(2, 4, 4, 5, 99, 0) to listOf(2, 4, 4, 5, 99, 9801),
            listOf(1, 1, 1, 4, 99, 5, 6, 0, 99) to listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
        )
        assertions.forEach {
            Assertions.assertEquals(
                it.second.map(Int::toBigInteger),
                intCodeExec(it.first.map(Int::toBigInteger)).code
            )
        }
    }
}