package year_2022

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class Day2Test {
    private val day2 = Day2()
    private val input = day2.getInput()

    @Test
    fun solvePart1() {
        Assertions.assertThat(day2.solvePart1(input)).isEqualTo(15)
    }

    @Test
    fun solvePart2() {
        Assertions.assertThat(day2.solvePart2(input)).isEqualTo(12)
    }
}
