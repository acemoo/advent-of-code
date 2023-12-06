package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Test:
AbstractDayTest(
    Day6(),
    288L,
    71503L
) {
    private val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent().split("\n")

    private val races = Races(
        listOf(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        )
    )

    @Test
    fun parse() {
        assertThat(Races.parseLines(input)).isEqualTo(
            races
        )
    }

    @Test
    fun calculateValidOptionsCount() {
        assertThat(Race(7, 9).findLowestValidOption()).isEqualTo(4)
    }

    @Test
    fun parseRace() {
        assertThat(Race.parseLines(input)).isEqualTo(
            Race(71530, 940200)
        )
    }

    @Test
    fun findLowestValidOption() {
        assertThat(Race(7, 9).findLowestValidOption()).isEqualTo(2)
    }

    @Test
    fun findHighestValidOption() {
        assertThat(Race(7, 9).findHighestValidOption()).isEqualTo(5)
    }
}
