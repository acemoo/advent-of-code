package year_2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day2Test {
    private val day2 = Day2()
    private val input = day2.getInput()

    @Test
    fun solvePart1() {
        assertThat(day2.solvePart1(input)).isEqualTo(150)
    }

    @Test
    fun solvePart2() {
        assertThat(day2.solvePart2(input)).isEqualTo(900)
    }
}
