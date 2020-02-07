import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day10Tests {
    @Test
    fun `task 1 small test`() {
        val input = ".#..#\n" +
                ".....\n" +
                "#####\n" +
                "....#\n" +
                "...##"

        val expected = ".7..7\n" +
                ".....\n" +
                "67775\n" +
                "....7\n" +
                "...87"

        val asteroids = parseAsteroidField(input)
        val maxX = asteroids.maxBy { it.x }!!.x
        val maxY = asteroids.maxBy { it.y }!!.y

        Assertions.assertEquals(8, asteroidsInView(asteroids, 3, 4, maxX, maxY).size)
        Assertions.assertEquals(7, asteroidsInView(asteroids, 1, 0, maxX, maxY).size)
        Assertions.assertEquals(7, asteroidsInView(asteroids, 4, 0, maxX, maxY).size)
        Assertions.assertEquals(6, asteroidsInView(asteroids, 0, 2, maxX, maxY).size)
        Assertions.assertEquals(7, asteroidsInView(asteroids, 1, 2, maxX, maxY).size)
        Assertions.assertEquals(7, asteroidsInView(asteroids, 2, 2, maxX, maxY).size)
        Assertions.assertEquals(7, asteroidsInView(asteroids, 3, 2, maxX, maxY).size)
        Assertions.assertEquals(5, asteroidsInView(asteroids, 4, 2, maxX, maxY).size)
        Assertions.assertEquals(7, asteroidsInView(asteroids, 4, 4, maxX, maxY).size)
        Assertions.assertEquals(Point(3, 4) to 8, evalAsteroidField(asteroids))
    }

    @TestFactory
    fun `task 1 examples`() = listOf(
        "......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####" to (Point(5, 8) to 33),
        "#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###." to (Point(1, 2) to 35),
        ".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#..\n" +
                "\n" to (Point(6, 3) to 41),
        ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##" to (Point(11, 13) to 210)
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("10.1 tester: input $input, expected $expected") {
            Assertions.assertEquals(expected, evalAsteroidField(parseAsteroidField(input)))
        }
    }

    @Test
    fun `angle sorting`() {

        val c = Point(0, 0)
        val list = listOf(
            Point(0, 1),
            Point(1, 0),
            Point(-1, 0),
            Point(0, -1)
        )

        val expected = listOf(
            Point(0, -1),   // above first
            Point(1, 0),    // right
            Point(0, 1),    // down
            Point(-1, 0)    // left
        )

        Assertions.assertEquals(expected, list.sortedBy { laserAngle(c, it) })
    }

    @Test
    fun `task 2 example`() {
        val input = ".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##"

        val (x, y) = vaporizeAsteroids(parseAsteroidField(input), Point(11, 13), stopAt = 200)

        Assertions.assertEquals(802, x * 100 + y)
    }
}