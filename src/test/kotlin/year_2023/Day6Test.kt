package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class Day6Test:
AbstractDayTest(
    Day6(),
    288,
    71503
) {
    val input = """
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

    @TestFactory
    fun calculateValidOptions() =
        listOf(
            Race(7, 9) to listOf(2, 3, 4, 5),
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input should have $expected as valid options") {
                assertThat(input.calculateValidOptions()).isEqualTo(expected)
            }
        }

    @Test
    fun calculateValidOptionsCount() {
        assertThat(Race(7, 9).calculateValidOptionsCount()).isEqualTo(4)
    }

    @Test
    fun parseRace() {
        assertThat(Race.parseLines(input)).isEqualTo(
            Race(71530, 940200)
        )
    }
}
