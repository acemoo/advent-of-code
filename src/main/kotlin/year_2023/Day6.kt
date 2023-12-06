package year_2023

class Day6: Day(6) {
    override fun solvePart1(input: List<String>) =
        Races.parseLines(input)
            .numberOfWaysToBeatTheRecord()

    override fun solvePart2(input: List<String>) =
        Race.parseLines(input)
            .calculateValidOptionsCount()
}

data class Races(
    val races: List<Race>,
) {
    fun numberOfWaysToBeatTheRecord() =
        races.fold(1) { acc: Long, race: Race ->
            acc * race.calculateValidOptionsCount()
        }


    companion object {
        fun parseLines(lines: List<String>): Races {
            val numbers = lines.map { line ->
                line.split(" ")
                    .drop(1)
                    .filter(String::isNotBlank)
                    .map(String::toLong)
            }
            return Races(numbers.first().mapIndexed { index, number ->
                Race(number, numbers[1][index])
            })
        }
    }
}

data class Race(
    val timeLimit: Long,
    val highScore: Long,
) {
    fun calculateValidOptions(): List<Long> =
        (1 ..< timeLimit)
            .filter { holdTime ->
                (timeLimit - holdTime) * holdTime > highScore
            }

    fun calculateValidOptionsCount() =
        calculateValidOptions().size

    companion object {
      private fun lineToNumber(line: String) =
            line.split(" ")
                .drop(1)
                .filter(String::isNotBlank)
                .reduce { acc, part ->
                    acc + part
                }.toLong()

        fun parseLines(lines: List<String>) =
            Race(
                lineToNumber(lines[0]),
                lineToNumber(lines[1]),
            )
    }
}
