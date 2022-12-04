package year_2022

class Day4: Day(4) {
    override fun solvePart1(input: List<String>) =
        solvePart(input, ::overlapFully)

    override fun solvePart2(input: List<String>) =
        solvePart(input, ::overlapAtAll)

    private fun solvePart(input: List<String>, checkFunction: (IntRange, IntRange) -> Boolean) =
        input.map { line ->
            val ranges = line.split(",")
                .map {
                    val (start, end) = it.split("-")
                    start.toInt() .. end.toInt()
                }
            checkFunction(ranges[0], ranges[1])
        }
            .count { it }

    private fun overlapFully(one: IntRange, other: IntRange) =
        one.contains(other.first) && one.contains(other.last)
                || other.contains(one.first) && other.contains(one.last)

    private fun overlapAtAll(one: IntRange, other: IntRange) =
        one.asIterable().any { other.contains(it) }
                || other.asIterable().any { one.contains(it) }
}
