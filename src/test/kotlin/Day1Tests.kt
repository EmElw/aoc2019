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