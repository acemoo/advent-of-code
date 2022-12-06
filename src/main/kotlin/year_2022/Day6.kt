package year_2022

class Day6: Day(6) {
    override fun solvePart1(input: List<String>) =
        findMarker(input, 4, "start-of-packet")

    private fun findMarker(input: List<String>, distinctCount: Int, markerName: String): Int {
        input.first()
            .windowed(distinctCount)
            .forEachIndexed { index, characters ->
                if (characters
                        .map { char ->
                            characters.count { it == char }
                        }
                        .none { it > 1 }
                ) {
                    return index + distinctCount
                }
            }
        throw Exception("No $markerName marker found")
    }

    override fun solvePart2(input: List<String>) =
        findMarker(input, 14, "start-of-message")
}
