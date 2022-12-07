package year_2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day3Test {
    private val day3 = Day3()

    @Test
    fun solvePart1Example01() {
        assertThat(day3.solvePart1(">")).isEqualTo(2)
    }

    @Test
    fun solvePart1Example02() {
        assertThat(day3.solvePart1("^>v<")).isEqualTo(4)
    }

    @Test
    fun solvePart1Example03() {
        assertThat(day3.solvePart1("^v^v^v^v^v")).isEqualTo(2)
    }
}
