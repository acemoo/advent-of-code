import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

abstract class AbstractDayTest(
    private val day: Day,
    private val result1: Any,
    private val result2: Any,
) {
    @Test
    fun solvePart1() {
        assertThat(day.solvePart1(day.getInput())).isEqualTo(result1)
    }

    @Test
    fun solvePart2() {
        assertThat(day.solvePart2(day.getInput(2))).isEqualTo(result2)
    }
}
