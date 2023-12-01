package year_2023

import spelledDigitsToDigits


class Day1: Day(1) {
    override fun solvePart1(input: List<String>) =
        input.sumOf {
            getNumber(it)
        }

    override fun solvePart2(input: List<String>) =
        input.sumOf { line ->
            getNumber(line.spelledDigitsToDigits())
        }

    private fun getNumber(input: String) =
        10 * getFirstDigit(input) + getLastDigit(input)

    private fun getFirstDigit(input: String) =
        input.first { it.isDigit() }.digitToInt()

    private fun getLastDigit(input: String) =
        input.last { it.isDigit() }.digitToInt()
}
