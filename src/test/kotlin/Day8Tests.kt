import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day8Tests {
    @Test
    fun `count digit test`() {
        Assertions.assertEquals(
            3,
            Layer("123123123", 3).countDigit(1)
        )
        Assertions.assertEquals(
            3,
            Layer("333333111", 3).countDigit(1)
        )
    }

    @Test
    fun `decode example`() {
        Assertions.assertEquals(
            "0110", decodeSIF(
                listOf(
                    Layer("0222", 4),
                    Layer("1122", 4),
                    Layer("2212", 4),
                    Layer("0000", 4)
                )
            ).string
        )
    }
}