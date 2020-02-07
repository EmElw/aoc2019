import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class Day12Tests {


    @Test
    fun `Axis equality`(){
    }

    @Test
    fun `Moon Sim`() {
        val initialMoons = ("pos=<x=-1, y=  0, z= 2>, vel=<x= 0, y= 0, z= 0>\n" +
                "pos=<x= 2, y=-10, z=-7>, vel=<x= 0, y= 0, z= 0>\n" +
                "pos=<x= 4, y= -8, z= 8>, vel=<x= 0, y= 0, z= 0>\n" +
                "pos=<x= 3, y=  5, z=-1>, vel=<x= 0, y= 0, z= 0>").split("\n").map(::parseMoon)

        val newMoons = simulate(initialMoons, 1)

        val xPeriod = findAxisPeriod(0, initialMoons)
        val yPeriod = findAxisPeriod(1, initialMoons)
        val zPeriod = findAxisPeriod(2, initialMoons)

        val xRepeat = simulate(initialMoons, xPeriod)
        Assertions.assertTrue(initialMoons.zip(xRepeat).all { (lh, rh) ->
            lh.axisEquality(0, rh)
        })
        val yRepeat = simulate(initialMoons, yPeriod)
        Assertions.assertTrue(initialMoons.zip(xRepeat).all { (lh, rh) ->
            lh.axisEquality(1, rh)
        })
        val zRepeat = simulate(initialMoons, zPeriod)
        Assertions.assertTrue(initialMoons.zip(xRepeat).all { (lh, rh) ->
            lh.axisEquality(2, rh)
        })

        val count = findRepeatingState(initialMoons)


        Assertions.assertEquals(count, 2772)

        val expectedMoons = ("pos=<x= 2, y=-1, z= 1>, vel=<x= 3, y=-1, z=-1>\n" +
                "pos=<x= 3, y=-7, z=-4>, vel=<x= 1, y= 3, z= 3>\n" +
                "pos=<x= 1, y=-7, z= 5>, vel=<x=-3, y= 1, z=-3>\n" +
                "pos=<x= 2, y= 2, z= 0>, vel=<x=-1, y=-3, z= 1>").split("\n").map(::parseMoon)

        newMoons.zip(expectedMoons).forEach { (new, exp) -> Assertions.assertEquals(exp, new) }

        val tenSteps = simulate(initialMoons, 10)
        Assertions.assertEquals(179, calcSystemEnergy(tenSteps))
    }
}