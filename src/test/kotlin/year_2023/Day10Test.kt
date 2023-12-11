package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import utils.grid.Location
import kotlin.test.Test

class Day10Test:
AbstractDayTest(
    Day10(),
    8,
    0
) {
    private val squareLoopInput = """
        .....
        .F-7.
        .|.|.
        .L-J.
        .....
    """.trimIndent().split("\n")

    private val squareLoopWithStartInput = """
        .....
        .S-7.
        .|.|.
        .L-J.
        .....
    """.trimIndent().split("\n")

    private val pipe11 = Day10.Pipe(Location(1, 1), "S")
    private val pipe12 = Day10.Pipe(Location(1, 2), "═", Location(1, 1), Location(1, 3))
    private val pipe13 = Day10.Pipe(Location(1, 3), "╗", Location(1, 2), Location(2, 3))
    private val pipe21 = Day10.Pipe(Location(2, 1), "‖", Location(1, 1), Location(3, 1))
    private val pipe23 = Day10.Pipe(Location(2, 3), "‖", Location(1, 3), Location(3, 3))
    private val pipe31 = Day10.Pipe(Location(3, 1), "╚", Location(2, 1), Location(3, 2))
    private val pipe32 = Day10.Pipe(Location(3, 2), "═", Location(3, 1), Location(3, 3))
    private val pipe33 = Day10.Pipe(Location(3, 3), "╝", Location(3, 2), Location(2, 3))

    @TestFactory
    fun parse() =
        listOf(
            squareLoopInput to "     \n ╔═╗ \n ‖ ‖ \n ╚═╝ \n     \n",
            squareLoopWithStartInput to "     \n S═╗ \n ‖ ‖ \n ╚═╝ \n     \n",
        ).mapIndexed { index, (input, expected) ->
            DynamicTest.dynamicTest("$index") {
                assertThat(Day10.PipeMaze.parseLines(input).toString()).isEqualTo(expected)
            }
    }

    @Test
    fun getPipesConnectedToStart() {
        assertThat(Day10.PipeMaze.parseLines(squareLoopWithStartInput).getPipesConnectedToStart()).containsExactlyInAnyOrder(
            pipe12,
            pipe21,
        )
    }

    @Test
    fun getOtherConnectedPipe() {
        assertThat(Day10.PipeMaze.parseLines(squareLoopWithStartInput).getOtherConnectedPipeOrNull(
            pipe12,
            pipe11.location
        )).isEqualTo(pipe13)
    }

    @Test
    fun followPipe() {
        assertThat(Day10.PipeMaze.parseLines(squareLoopWithStartInput).findPiping()).containsExactlyInAnyOrder(
            pipe11, pipe12, pipe13,
            pipe21, pipe23,
            pipe31, pipe32, pipe33
        )
    }
}
