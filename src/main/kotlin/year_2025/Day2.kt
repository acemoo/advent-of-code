package year_2025

class Day2: Day(2) {
    override fun solvePart1(input: List<String>) =
        InvalidIDFinder(input).addUpAllInvalidIds()

    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }

    class InvalidIDFinder(
        input: List<String>,
    ) {
        val ranges = input.first().split(",")

        fun addUpAllInvalidIds(): Long =
            ranges.flatMap(::getIds)
                .mapNotNull { id ->
                    if (!isValid(id)) {
                        id
                    } else {
                        null
                    }
                }
                .sum()

        fun getIds(range: String): Set<Long> {
            val (from, to) = range.split("-").map { it.toLong() }
            return (from..to).map { it }.toSet()
        }

        fun isValid(id: Long): Boolean {
            val idString = id.toString()
            val firstSequence = idString.take(idString.length / 2)
            val lastSequence = idString.drop(idString.length / 2)
            return firstSequence != lastSequence
        }
    }
}
