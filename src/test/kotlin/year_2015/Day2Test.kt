package year_2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day2Test {
    private val day2 = Day2()

    @Test
    fun solvePart1Example01() {
        assertThat(day2.solvePart1("2x3x4")).isEqualTo(58)
    }

    @Test
    fun solvePart1Example02() {
        assertThat(day2.solvePart1("1x1x10")).isEqualTo(43)
    }

    @Test
    fun solvePart2Example01() {
        assertThat(day2.solvePart2("2x3x4")).isEqualTo(34)
    }

    @Test
    fun solvePart2Example02() {
        assertThat(day2.solvePart2("1x1x10")).isEqualTo(14)
    }
}
