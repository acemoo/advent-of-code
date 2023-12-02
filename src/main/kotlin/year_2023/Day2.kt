package year_2023

class Day2: Day(2) {
    private val actualRed = 12
    private val actualGreen = 13
    private val actualBlue = 14

    override fun solvePart1(input: List<String>) =
        input.mapNotNull { line ->
            val game = Game.parseLine(line)
            if (game.isPossible(actualRed, actualGreen, actualBlue)) {
                game.id
            } else {
                null
            }
        }.sum()

    override fun solvePart2(input: List<String>) =
        input.sumOf { line ->
            Game.parseLine(line).power()
        }
}

data class Game(
    val id: Int,
    val minimumRed: Int,
    val minimumGreen: Int,
    val minimumBlue: Int,
) {
    companion object {
        fun parseLine(line: String): Game {
            val idAndValues = line.substring(4)
                .split(":", ",", ";")
                .map { it.split(" ") }
            val maxValues = idAndValues.drop(1)
                .groupingBy { it.last() }
                .fold(0) { maxCubeCount: Int, element: List<String> ->
                    val currentCubeCount = element[1].toInt()
                    if (maxCubeCount > currentCubeCount) {
                        maxCubeCount
                    } else {
                        currentCubeCount
                    }
                }
            return Game(
                id = idAndValues.first().last().toInt(),
                minimumRed = maxValues["red"] ?: 0,
                minimumGreen = maxValues["green"] ?: 0,
                minimumBlue = maxValues["blue"] ?: 0
            )
        }
    }

    fun isPossible(actualRed: Int, actualGreen: Int, actualBlue: Int) =
        minimumRed <= actualRed &&
        minimumGreen <= actualGreen &&
        minimumBlue <= actualBlue

    fun power() =
        minimumRed * minimumGreen * minimumBlue
}
