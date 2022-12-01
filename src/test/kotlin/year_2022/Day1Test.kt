package year_2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day1Test {
    private val day1 = Day1()
    private val input = day1.getInput()

    @Test
    fun solvePart1() {
        assertThat(day1.solvePart1(input)).isEqualTo(24000)
    }

    @Test
    fun solvePart2() {
        assertThat(day1.solvePart2(input)).isEqualTo(45000)
    }
}
