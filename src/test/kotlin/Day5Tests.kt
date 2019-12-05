import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory


class Day5Tests {
    @Test
    fun `Example 1`() {
        Assertions.assertEquals(listOf("1337", "halted"), intCodeExec(listOf(3, 0, 4, 0, 99), 1337).second)
        Assertions.assertEquals(listOf(1002, 4, 3, 4, 99), intCodeExec(listOf(1002, 4, 3, 4, 33)).first)
    }

    @TestFactory
    fun `Less than tester`() = listOf(
        (listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8) to 4) to "1",   // less than 8
        (listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8) to 8) to "0",   // not less than 8
        (listOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8) to 10) to "0",   // not less than 8
        (listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99) to 4) to "1",   // less than 8
        (listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99) to 8) to "0",   // not less than 8
        (listOf(3, 3, 1107, -1, 8, 3, 4, 3, 99) to 10) to "0"   // not less than 8
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Equality tester: input $input, expected $expected") {
            Assertions.assertEquals(expected, intCodeExec(input.first, input.second).second[0])
        }
    }

    @TestFactory
    fun `Equality tester`() = listOf(
        (listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8) to 8) to "1",   // equal to 8
        (listOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8) to 4) to "0",   // not equal to 8
        (listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99) to 8) to "1",       // equal to 8 (immediate)
        (listOf(3, 3, 1108, -1, 8, 3, 4, 3, 99) to 4) to "0"       // not equal to 8 (immediate)
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Equality tester: input $input, expected $expected") {
            Assertions.assertEquals(expected, intCodeExec(input.first, input.second).second[0])
        }
    }

    @TestFactory
    fun `Jump tester`() = listOf(
        (listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9) to 0) to "0",  // outputs 0 if 0 or 1 if 1
        (listOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9) to 1) to "1"  // outputs 0 if 0 or 1 if 1
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Equality tester: input $input, expected $expected") {
            Assertions.assertEquals(expected, intCodeExec(input.first, input.second).second[0])
        }
    }

    private val bigExample = listOf(
        3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
        1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
        999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
    )

    @TestFactory
    fun `Big example`() = listOf(
        (bigExample to 7) to "999",
        (bigExample to 8) to "1000",
        (bigExample to 9) to "1001"
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Equality tester: input $input, expected $expected") {
            Assertions.assertEquals(expected, intCodeExec(input.first, input.second).second[0])
        }
    }

}