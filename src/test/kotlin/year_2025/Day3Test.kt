package year_2025

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test:
    AbstractDayTest(
        Day3(),
        357L,
        3121910778619L
    ) {

    @Test
    fun `818181911112111-2-92`() {
        assertThat(Day3.Bank("818181911112111").findHighestJoltage(2))
            .isEqualTo(92L)
    }

    @Test
    fun `234234234234278-2-78`() {
        assertThat(Day3.Bank("234234234234278").findHighestJoltage(2))
            .isEqualTo(78L)
    }

    @Test
    fun `811111111111119-2-89`() {
        assertThat(Day3.Bank("811111111111119").findHighestJoltage(2))
            .isEqualTo(89L)
    }

    @Test
    fun `12-2-12`() {
        assertThat(Day3.Bank("12").findHighestJoltage(2))
            .isEqualTo(12L)
    }

    @Test
    fun `2-1-2`() {
        assertThat(Day3.Bank("2").findHighestJoltage(1))
            .isEqualTo(2L)
    }

    @Test
    fun `811111111111119-12-811111111119`() {
        assertThat(Day3.Bank("811111111111119").findHighestJoltage(12))
            .isEqualTo(811111111119L)
    }

    @Test
    fun `987654321111111-2-98`() {
        assertThat(Day3.Bank("987654321111111").findHighestJoltage(2))
            .isEqualTo(98L)
    }

    @Test
    fun `212-2-22`() {
        assertThat(Day3.Bank("212").findHighestJoltage(2))
            .isEqualTo(22L)
    }
}
