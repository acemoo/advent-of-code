package year_2025

import kotlin.math.max
import kotlin.math.min

class Day5: Day(5) {
    override fun solvePart1(input: List<String>) =
        CafetariaInventoryManagementSystem(input)
            .countFreshIngredientIds()

    override fun solvePart2(input: List<String>) =
        CafetariaInventoryManagementSystem(input)
            .countFreshIds()


    class CafetariaInventoryManagementSystem(input: List<String>) {
        val freshIdRanges = input.filter { it.contains("-") }
            .map {
                it.split("-")
                    .run { LongRange(first().toLong(), last().toLong()) }
            }
        val ids = input.filter { it.toLongOrNull() != null }

        fun countFreshIngredientIds() =
            ids.count { id -> freshIdRanges.any { it.contains(id.toLong()) } }

        fun countFreshIds(): Long =
            toNonOverlappingRanges(freshIdRanges)
                .sumOf { it.last - it.first + 1}

        fun toNonOverlappingRanges(ranges: List<LongRange>): List<LongRange> {
            return toNonOverlappingRangesUsingSortedRanges(ranges.sortedBy { it.first })
        }

        fun toNonOverlappingRangesUsingSortedRanges(ranges: List<LongRange>): List<LongRange> {
            if (ranges.size == 1) {
                return ranges
            }
            val nonOverlappingRanges = mutableListOf<LongRange>()
            var currentRange = ranges.first()
            ranges.drop(1).forEachIndexed { index, range ->
                if (overlap(currentRange, range)) {
                    currentRange = merge(currentRange, range)
                } else {
                    nonOverlappingRanges.add(currentRange)
                    currentRange = range
                }
                if (ranges.lastIndex == index + 1) {
                    nonOverlappingRanges.add(currentRange)
                }
            }
            return nonOverlappingRanges
        }

        fun overlap(first: LongRange, second: LongRange): Boolean {
            return second.contains(first.first)
                    || second.contains(first.last)
                    || first.contains(second.first)
                    || first.contains(second.last)
        }

        fun merge(first: LongRange, second: LongRange): LongRange {
            return LongRange(
                min(first.first, second.first),
                max(first.last, second.last)
            )
        }
    }
}
