package year_2025

class Day2: Day(2) {
    override fun solvePart1(input: List<String>) =
        InvalidIDFinder().parseRanges(input).addUpAllDIdsWithDuplicateSequence()

    override fun solvePart2(input: List<String>) =
        InvalidIDFinder().parseRanges(input).addUpAllIdsWithRepatingSequence()

    class InvalidIDFinder() {
        var ranges = emptyList<String>()

        fun parseRanges(input: List<String>): InvalidIDFinder {
            ranges = input.first().split(",")
            return this
        }

        fun addUpAllIdsWithRepatingSequence(): Long =
            addUpAllInvalidIds(::isRepeatingSequence)

        fun addUpAllDIdsWithDuplicateSequence(): Long =
            addUpAllInvalidIds(::isDuplicateSequence)

        fun addUpAllInvalidIds(validatorFunction: (Long) -> Boolean): Long =
            ranges.flatMap(::getIds)
                .mapNotNull { id ->
                    if (validatorFunction(id)) {
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

        fun isDuplicateSequence(id: Long): Boolean {
            val idString = id.toString()
            val firstSequence = idString.take(idString.length / 2)
            val lastSequence = idString.drop(idString.length / 2)
            return firstSequence == lastSequence
        }

        fun isRepeatingSequence(id: Long): Boolean {
            val idString = id.toString()
            (1 .. idString.length / 2).forEach { chunkSize ->
                if (chunkedHasAtLeastTwoEntriesAndIsDistinct(idString, chunkSize)) {
                    return true
                }
            }
            return false
        }

        fun chunkedHasAtLeastTwoEntriesAndIsDistinct(id: String, num: Int): Boolean {
            val chunked = id.chunked(num)
            if (chunked.size == 1) {
                return false
            }
            if (chunked.distinct().size == 1) {
                return true
            }
            return false
        }
    }
}
