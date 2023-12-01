package year_2023

class Day1: Day(1) {
    override fun solvePart1(input: List<String>) =
        input.sumOf { line ->
            getNumber(line)
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

fun String.spelledDigitsToDigits() =
    windowed(5, 1, true) {
        when {
            it.startsWith("zero") -> "0"
            it.startsWith("one") -> "1"
            it.startsWith("two") -> "2"
            it.startsWith("three") -> "3"
            it.startsWith("four") -> "4"
            it.startsWith("five") -> "5"
            it.startsWith("six") -> "6"
            it.startsWith("seven") -> "7"
            it.startsWith("eight") -> "8"
            it.startsWith("nine") -> "9"
            else -> it.first().toString()
        }
    }.joinToString("")
