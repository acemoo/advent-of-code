package year_2015

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day1Test {
    private val day1 = Day1()

    @Test
    fun solvePart1Example01() {
        assertThat(day1.solvePart1(listOf("(())"))).isEqualTo(0)
    }

    @Test
    fun solvePart1Example02() {
        assertThat(day1.solvePart1(listOf("()()"))).isEqualTo(0)
    }

    @Test
    fun solvePart1Example03() {
        assertThat(day1.solvePart1(listOf("((("))).isEqualTo(3)
    }

    @Test
    fun solvePart1Example04() {
        assertThat(day1.solvePart1(listOf("(()(()("))).isEqualTo(3)
    }

    @Test
    fun solvePart1Example05() {
        assertThat(day1.solvePart1(listOf("))((((("))).isEqualTo(3)
    }

    @Test
    fun solvePart1Example06() {
        assertThat(day1.solvePart1(listOf("())"))).isEqualTo(-1)
    }

    @Test
    fun solvePart1Example07() {
        assertThat(day1.solvePart1(listOf("))("))).isEqualTo(-1)
    }

    @Test
    fun solvePart1Example08() {
        assertThat(day1.solvePart1(listOf(")))"))).isEqualTo(-3)
    }

    @Test
    fun solvePart1Example09() {
        assertThat(day1.solvePart1(listOf(")())())"))).isEqualTo(-3)
    }

    @Test
    fun solvePart2Example01() {
        assertThat(day1.solvePart2(listOf(")"))).isEqualTo(1)
    }

    @Test
    fun solvePart2Example02() {
        assertThat(day1.solvePart2(listOf("()())"))).isEqualTo(5)
    }
}
