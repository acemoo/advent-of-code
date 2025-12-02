package year_2025

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

internal class Day2Test:
    AbstractDayTest(
        Day2(),
        1227775554,
        0
    ) {

    @Test
    fun getIds() {
        assertThat(Day2.InvalidIDFinder(listOf("11-13")).getIds("11-13"))
            .containsExactly(11, 12, 13)
    }

    @TestFactory
    fun isValid() =
        listOf(
            11 to false,
            12 to true,
            13 to true,
            22 to false,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("${input}, should be $expected") {
                assertThat(Day2.InvalidIDFinder(listOf("11-13")).isValid(input))
                    .isEqualTo(expected)
            }
        }
}
