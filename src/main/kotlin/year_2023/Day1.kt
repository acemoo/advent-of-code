package year_2023


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

fun String.spelledDigitsToDigits() =
    windowed(5, 1, true) {
        return@windowed if (it.startsWith("one")) "1"
        else if (it.startsWith("two")) "2"
        else if (it.startsWith("three")) "3"
        else if (it.startsWith("four")) "4"
        else if (it.startsWith("five")) "5"
        else if (it.startsWith("six")) "6"
        else if (it.startsWith("seven")) "7"
        else if (it.startsWith("eight")) "8"
        else if (it.startsWith("nine")) "9"
        else it.first().toString()
    }.joinToString("")
