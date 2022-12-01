package year_2021

import Day

class Day1(year: Int): Day(year, 1) {

    override fun solvePart1(input: List<String>) =
        input
            .map { it.toInt() }
            .windowed(2)
            .fold(0) { value, (left, right) ->
                if (left < right) {
                    value + 1
                } else {
                    value
                }
            }

    override fun solvePart2(input: List<String>) =
        input
            .map { it.toInt() }
            .windowed(3)
            .zipWithNext()
            .fold(0) { value, (left, right) ->
                if (left.sum() < right.sum()) {
                    value + 1
                } else {
                    value
                }
            }
}
