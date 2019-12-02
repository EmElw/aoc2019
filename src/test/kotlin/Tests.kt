import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day1Tests {
    @Test
    fun `Day 1 task 1`() {
        Assertions.assertEquals(654, sumFuel(listOf(1969)))
        Assertions.assertEquals(33583, sumFuel(listOf(100756)))
    }

    @Test
    fun `Day 1 task 2`() {
        Assertions.assertEquals(966, sumFuelRec(listOf(1969)))
        Assertions.assertEquals(50346, sumFuelRec(listOf(100756)))
    }
}

class Day2Tests {
    @Test
    fun `Day 2 task 1`() {
        val assertions = listOf( // input -> expected
            Pair(listOf(1, 0, 0, 0, 99), listOf(2, 0, 0, 0, 99)),
            Pair(listOf(2, 3, 0, 3, 99), listOf(2, 3, 0, 6, 99)),
            Pair(listOf(2, 4, 4, 5, 99, 0), listOf(2, 4, 4, 5, 99, 9801)),
            Pair(listOf(1, 1, 1, 4, 99, 5, 6, 0, 99), listOf(30, 1, 1, 4, 2, 5, 6, 0, 99))
        )
        assertions.forEach {
            Assertions.assertEquals(
                it.second,
                intCodeExec(it.first)
            )
        }
    }
}