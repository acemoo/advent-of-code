import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class AbstractDayTest(
    private val day: Day,
    private val result1: Any,
    private val result2: Any,
) {
    private val input = day.getInput()

    @Test
    fun solvePart1() {
        assertThat(day.solvePart1(input)).isEqualTo(result1)
    }

    @Test
    fun solvePart2() {
        assertThat(day.solvePart2(input)).isEqualTo(result2)
    }
}
