package year_2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day4Test {
    private val day4 = Day4()
    private val input = day4.getInput()

    @Test
    fun solvePart1() {
        assertThat(day4.solvePart1(input)).isEqualTo(4512)
    }

    @Test
    fun solvePart2() {
        assertThat(day4.solvePart2(input)).isEqualTo(1924)
    }
}
