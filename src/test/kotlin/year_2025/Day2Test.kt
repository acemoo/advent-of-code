package year_2025

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class Day2Test:
    AbstractDayTest(
        Day2(),
        1227775554L,
        4174379265L
    ) {

    @Test
    fun getIds() {
        assertThat(Day2.InvalidIDFinder().getIds("11-13"))
            .containsExactly(11, 12, 13)
    }

    @TestFactory
    fun isDuplicateSequence() =
        listOf(
            11L to true,
            12L to false,
            13L to false,
            22L to true,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("${input}, should be $expected") {
                assertThat(Day2.InvalidIDFinder().isDuplicateSequence(input))
                    .isEqualTo(expected)
            }
        }

    @TestFactory
    fun isRepeatingSequence() =
        listOf(
            11L to true,
            12L to false,
            1212L to true,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("${input}, should be $expected") {
                assertThat(Day2.InvalidIDFinder().isRepeatingSequence(input))
                    .isEqualTo(expected)
            }
        }

    @TestFactory
    fun chunkedHasAtLeastTwoEntriesAndIsDistinct() =
        listOf(
            Triple("11", 1, true),
            Triple("11", 2, false),
            Triple("12", 1, false),
            Triple("1212", 2, true),
        ).map { (input, chunkSize, expected) ->
            DynamicTest.dynamicTest("$input, $chunkSize, should be $expected") {
                assertThat(Day2.InvalidIDFinder().chunkedHasAtLeastTwoEntriesAndIsDistinct(input, chunkSize))
                    .isEqualTo(expected)
            }
        }
}
