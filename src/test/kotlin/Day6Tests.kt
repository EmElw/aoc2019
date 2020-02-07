import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day6Tests {
    @Test
    fun `Day 6 example 1`() {
        Assertions.assertEquals(
            42, checkSumOrbits(
                "COM)B\n" +
                        "B)C\n" +
                        "C)D\n" +
                        "D)E\n" +
                        "E)F\n" +
                        "B)G\n" +
                        "G)H\n" +
                        "D)I\n" +
                        "E)J\n" +
                        "J)K\n" +
                        "K)L"
            )
        )
        Assertions.assertEquals(251208, checkSumOrbits(getInput(6)))
    }

    @Test
    fun `Day 6 example 2`() {
        Assertions.assertEquals(
            4, orbitalDistance(
                "COM)B\n" +
                        "B)C\n" +
                        "C)D\n" +
                        "D)E\n" +
                        "E)F\n" +
                        "B)G\n" +
                        "G)H\n" +
                        "D)I\n" +
                        "E)J\n" +
                        "J)K\n" +
                        "K)L\n" +
                        "K)YOU\n" +
                        "I)SAN"
            )
        )
        Assertions.assertEquals(
            397, orbitalDistance(getInput(6))
        )
    }
}