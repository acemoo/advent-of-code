package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test

class Day9Test:
AbstractDayTest(
    Day9(),
    114,
    2
){

    private val input = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent().split("\n")

    private val history1 = Day9.History.of(0, 3, 6, 9, 12, 15)
    private val history2 = Day9.History.of(1, 3, 6, 10, 15, 21)
    private val history3 = Day9.History.of(10, 13, 16, 21, 30, 45)

    private val report = Day9.Report(listOf(history1, history2, history3))

    @Test
    fun parse() {
        assertThat(Day9.Report.parse(input)).isEqualTo(report)
    }

    @TestFactory
    fun lastSteps() =
        listOf(
            history1 to listOf(15, 3, 0),
            history2 to listOf(21, 6, 1, 0),
            history3 to listOf(45, 15, 6, 2, 0),
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input to $expected") {
                assertThat(input.calculateLastSteps()).isEqualTo(expected)
            }
        }

    @TestFactory
    fun firstSteps() =
        listOf(
            history1 to listOf(0, 3, 0),
            history2 to listOf(1, 2, 1, 0),
            history3 to listOf(10, 3, 0, 2, 0),
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input to $expected") {
                assertThat(input.calculateFirstSteps()).isEqualTo(expected)
            }
        }

    @TestFactory
    fun extrapolateNextValue() =
        listOf(
            history1 to 18,
            history2 to 28,
            history3 to 68,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input to $expected") {
                assertThat(input.extrapolateNextValue()).isEqualTo(expected)
            }
        }

    @TestFactory
    fun extrapolatePreviousValue() =
        listOf(
            history1 to -3,
            history2 to 0,
            history3 to 5,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input to $expected") {
                assertThat(input.extrapolatePreviousValue()).isEqualTo(expected)
            }
        }
}
