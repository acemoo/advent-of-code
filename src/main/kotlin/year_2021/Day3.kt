package year_2021

class Day3: Day(3) {
    override fun solvePart1(input: List<String>) =
        input
            .map { line -> line.map { char -> char - '0' } }
            .reduce { total, next ->
                total.mapIndexed { index, i ->
                    i + next[index]
                }
            }
            .fold("") { accumulated, next ->
                accumulated + (next > input.size / 2).compareTo(false)
            }
            .let {
                it.toInt(2) *
                    it
                        .replace("0", "A")
                        .replace("1", "0")
                        .replace("A", "1")
                        .toInt(2)
            }

    override fun solvePart2(input: List<String>): Int {
        val inputAsListOfInts = mapToListOfInts(input)
        return oxygenGeneratorRating(inputAsListOfInts) *
                co2ScrubberRating(inputAsListOfInts)
    }

    private fun co2ScrubberRating(input: List<List<Int>>) =
         filterCO2(input, 0)
             .joinToString("")
             .toInt(2)

    private fun mapToListOfInts(input: List<String>) =
        input.map { line -> line.map { char -> char - '0' } }

    private fun oxygenGeneratorRating(input: List<List<Int>>) =
        filterOxygen(input,0)
                .joinToString("")
                .toInt(2)

    private fun filterCO2(input: List<List<Int>>, index: Int): List<Int> {
        val numberToKeep = if (mostCommonValue(input, index) < 0) {1} else {0}
        val result = input.filter { it[index] == numberToKeep }
        return if (result.size == 1) {
            result[0]
        } else {
            filterCO2(result, index + 1)
        }
    }

    private fun filterOxygen(input: List<List<Int>>, index: Int): List<Int> {
        val numberToKeep = if (mostCommonValue(input, index) >= 0) {1} else {0}
        val result = input.filter { it[index] == numberToKeep }
        return if (result.size == 1) {
            result[0]
        } else {
            filterOxygen(result, index + 1)
        }
    }

    private fun mostCommonValue(input: List<List<Int>>, index: Int) =
        input
            .reduce { total, next ->
                total.mapIndexed { index, i ->
                    i + next[index]
                }
            }
            .let {
                it[index].compareTo((input.size / 2.0))
            }
}
