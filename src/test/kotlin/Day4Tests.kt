import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class Day4Tests {
    @TestFactory
    fun `Day 4 task 1`() = listOf(
        "699065" to false,
        "111111" to true,
        "223450" to false,
        "123789" to false
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Day 4 task 1: input $input, expected $expected") {
            Assertions.assertEquals(expected, criteria(input))
        }
    }

    @TestFactory
    fun `Day 4 task 2`() = listOf(
        "699065" to false,
        "111111" to false,
        "223450" to false,
        "123789" to false,
        "112233" to true,
        "123444" to false,
        "111122" to true
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Day 4 task 1: input $input, expected $expected") {
            Assertions.assertEquals(expected, criteria2(input))
        }
    }
}