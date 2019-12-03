import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.function.Executable


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
            listOf(1, 0, 0, 0, 99) to listOf(2, 0, 0, 0, 99),
            listOf(2, 3, 0, 3, 99) to listOf(2, 3, 0, 6, 99),
            listOf(2, 4, 4, 5, 99, 0) to listOf(2, 4, 4, 5, 99, 9801),
            listOf(1, 1, 1, 4, 99, 5, 6, 0, 99) to listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
        )
        assertions.forEach {
            Assertions.assertEquals(
                it.second,
                intCodeExec(it.first)
            )
        }
    }
}

class Day3Tests {

    @Test
    fun `Manhattan distance test`() {
        Assertions.assertEquals(6, Point(3, 3) mhd Point(0, 0))
        Assertions.assertEquals(10, Point(5, 2) mhd Point(-3, 4))
        Assertions.assertEquals(2, Point(5, -5) mhd Point(3, -5))
    }

    @TestFactory
    fun `Day 3 task 1`() = listOf(
        "R8,U5,L5,D3\n" +
                "U7,R6,D4,L4" to 6,
        "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n" +
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7" to 135,
        "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R83" to 159,
        getInput(3) to 5357
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Day 3 task 1: input $input, expected $expected") {
            Assertions.assertEquals(expected, solve3task1(processInput(input)))
        }
    }
    @TestFactory
    fun `Day 3 task 2`() = listOf(
        "R8,U5,L5,D3\nU7,R6,D4,L4" to 30,
        "R75,D30,R83,U83,L12,D49,R71,U7,L72\n" +
                "U62,R66,U55,R34,D71,R55,D58,R83" to 610,
        "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\n" +
                "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7" to 410,
        getInput(3) to 101956
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Day 3 task 2: input $input, expected $expected") {
            Assertions.assertEquals(expected, solve3task2(processInput(input)))
        }
    }
}