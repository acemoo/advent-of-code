package year_2022

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day6Test {
    private val day = Day6()

    @Test
    fun solvePart1Example01() {
        assertThat(day.solvePart1("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(5)
    }

    @Test
    fun solvePart1Example02() {
        assertThat(day.solvePart1("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(6)
    }

    @Test
    fun solvePart1Example03() {
        assertThat(day.solvePart1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(10)
    }

    @Test
    fun solvePart1Example04() {
        assertThat(day.solvePart1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(11)
    }

    @Test
    fun solvePart2Example01() {
        assertThat(day.solvePart2("mjqjpqmgbljsphdztnvjfqwrcgsmlb")).isEqualTo(19)
    }

    @Test
    fun solvePart2Example02() {
        assertThat(day.solvePart2("bvwbjplbgvbhsrlpgdmjqwftvncz")).isEqualTo(23)
    }

    @Test
    fun solvePart2Example03() {
        assertThat(day.solvePart2("nppdvjthqldpwncqszvftbrmjlhg")).isEqualTo(23)
    }

    @Test
    fun solvePart2Example04() {
        assertThat(day.solvePart2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")).isEqualTo(29)
    }

    @Test
    fun solvePart2Example05() {
        assertThat(day.solvePart2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")).isEqualTo(26)
    }
}
