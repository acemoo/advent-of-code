package year_2021

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {
    private val day3 = Day3(2021)
    private val input = day3.getInput()

    @Test
    fun solvePart1() {
        assertThat(day3.solvePart1(input)).isEqualTo(198)
    }

    @Test
    fun solvePart2() {
        assertThat(day3.solvePart2(input)).isEqualTo(230)
    }
}
