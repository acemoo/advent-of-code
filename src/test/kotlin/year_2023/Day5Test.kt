package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import year_2023.Almanac.Mapping
import year_2023.Almanac.Range

class Day5Test : AbstractDayTest(
    Day5(),
    35L,
    46L
) {
    private val seedToSoilMapping = Mapping(
        "seed",
        "soil",
        listOf(
            Range(
                LongRange(98, 99),
                LongRange(50, 51),
                -48,
            ),
            Range(
                LongRange(50, 97),
                LongRange(52, 99),
                2,
            ),
        )
    )
    private val soilToFertilizerMapping = Mapping(
        "soil",
        "fertilizer",
        listOf(
            Range(
                LongRange(15, 51),
                LongRange(0, 36),
                -15,
            ),
            Range(
                LongRange(52, 53),
                LongRange(37, 38),
                -15,
            ),
            Range(
                LongRange(0, 14),
                LongRange(39, 53),
                39,
            )
        )
    )

    private val almanac = Almanac(
        listOf(79, 14, 55, 13),
        listOf(
            LongRange(79L, 93L),
            LongRange(55L, 68L),
        ),
        mapOf(
            "seed" to seedToSoilMapping,
            "soil" to soilToFertilizerMapping,
        )
    )

    @Test
    fun parsing() {
        val input = """
                seeds: 79 14 55 13

                seed-to-soil map:
                50 98 2
                52 50 48

                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
            """.trimIndent().split("\n")
        assertThat(Almanac.parseLines(input)).isEqualTo(almanac)
    }

    @TestFactory
    fun mappingMapsTo() =
        listOf(
            1L to 1L,
            50L to 52L,
            97L to 99L,
            98L to 50L,
            99L to 51L,
            100L to 100L,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input maps to $expected") {
                assertThat(seedToSoilMapping.map(input)).isEqualTo(expected to "soil")
            }
        }

    @TestFactory
    fun almanacMapsTo() =
        listOf(
            40L to 25L,
            50L to 37L,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input maps to $expected") {
                assertThat(almanac.map(input)).isEqualTo(expected)
            }
        }

}
