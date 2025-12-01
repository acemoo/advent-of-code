package year_2024

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

internal class Day2Test:
AbstractDayTest(
    Day2(),
    2,
    4
) {
    @Test
    fun `all levels decrease by 1 or 2 should be safe`() {
        assertThat(Day2.Report("7 6 4 2 1".split(' ').map { it.toInt() }).isSafe()).isTrue()
    }

    @Test
    fun `all levels increase by 1, 2 or 3 should be safe`() {
        assertThat(Day2.Report("1 3 6 7 9".split(' ').map { it.toInt() }).isSafe()).isTrue()
    }

    @Test
    fun `increase of more than 3 should be unsafe`() {
        assertThat(Day2.Report("1 2 7 8 9".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `decrease of more than 3 should be unsafe`() {
        assertThat(Day2.Report("9 7 6 2 1".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `increase then decrease should be unsafe`() {
        assertThat(Day2.Report("1 3 2 4 5".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `decrease then increase should be unsafe`() {
        assertThat(Day2.Report("5 4 2 3 1".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `no increase or decrease should be unsafe`() {
        assertThat(Day2.Report("8 6 4 4 1".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `using dampener, 7 6 4 2 1 should be safe`() {
        assertThat(Day2.Report("7 6 4 2 1".split(' ').map { it.toInt() }).isSafe()).isTrue()
    }

    @Test
    fun `using dampener, 1 2 7 8 9 should be unsafe`() {
        assertThat(Day2.Report("1 2 7 8 9".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `using dampener, 9 7 6 2 1 should be unsafe`() {
        assertThat(Day2.Report("9 7 6 2 1".split(' ').map { it.toInt() }).isSafe()).isFalse()
    }

    @Test
    fun `using dampener, increase then a single decrease but then all increase again should be safe`() {
        assertThat(Day2.Report("1 3 2 4 5".split(' ').map { it.toInt() }).isSafe(true)).isTrue()
    }

    @Test
    fun `using dampener, 8 6 4 4 1 should be safe`() {
        assertThat(Day2.Report("8 6 4 4 1".split(' ').map { it.toInt() }).isSafe(true)).isTrue()
    }

    @Test
    fun `using dampener, 1 3 6 7 9 should be safe`() {
        assertThat(Day2.Report("1 3 6 7 9".split(' ').map { it.toInt() }).isSafe(true)).isTrue()
    }

    @Test
    fun `using dampener, 2 2 4 6 9 should be safe`() {
        assertThat(Day2.Report("2 2 4 6 9".split(' ').map { it.toInt() }).isSafe(true)).isTrue()
    }
}
