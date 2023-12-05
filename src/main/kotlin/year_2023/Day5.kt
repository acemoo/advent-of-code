package year_2023

import groupConsecutiveBy
import kotlin.math.min

class Day5: Day(5) {
    override fun solvePart1(input: List<String>) =
        Almanac.parseLines(input)
            .findLowest()

    override fun solvePart2(input: List<String>) =
        "Disabled for being too slow (5+ minutes)"
//        Almanac.parseLines(input)
//            .findLowestViaRanges()
}

data class Almanac(
    val seeds: List<Long>,
    val seedRanges: List<LongRange>,
    val mappings: Map<String, Mapping>,
) {

    companion object {
        fun parseLines(lines: List<String>): Almanac {
            val groupedLines = lines.groupConsecutiveBy(String::isNotBlank)
            val seeds = lines.first()
                .substring(7)
                .split(" ")
                .map(String::toLong)

            val seedRanges = seeds.windowed(2, 2)
                .map { LongRange(it[0], it[0] + it[1]) }

            val mappings = groupedLines.drop(1)
                .associate { mapLines ->
                    val (source, _, destination) = mapLines.first().split(" ").first().split("-")
                    val ranges = mapLines.drop(1)
                        .map { line ->
                            val (destinationStart, sourceStart, length) = line.split(" ")
                                .map(String::toLong)
                            Range(
                                sourceStart.rangeUntil(sourceStart + length),
                                destinationStart.rangeUntil(destinationStart + length),
                                destinationStart - sourceStart,
                            )
                        }
                    source to Mapping(source, destination, ranges)
            }
            return Almanac(seeds, seedRanges, mappings)
        }
    }

    fun findLowest() =
        seeds.map { seed ->
            map(seed)
        }.minOf { it }

    fun findLowestViaRanges() =
        seedRanges.fold(Long.MAX_VALUE) { lowestRange, seedRange ->
            min(lowestRange, seedRange.fold(Long.MAX_VALUE) { lowestSeed, seed ->
                min(lowestSeed, map(seed))
            })
        }

    fun map(input: Long, source: String?) =
        mappings[source]?.map(input) ?: (input to null)

    fun map(input: Long): Long {
        var result: Pair<Long, String?> = input to "seed"
        do {
            result = map(result.first, result.second)
        } while (result.second != null)
        return result.first
    }

    data class Mapping(
        val source: String,
        val destination: String,
        val ranges: List<Range>,
    ) {
        fun map(input: Long) =
            (ranges.firstOrNull { range ->
                range.sourceRange.contains(input)
            }?.jump ?: 0) + input to destination
    }

    data class Range(
        val sourceRange: LongRange,
        val destinationRange: LongRange,
        val jump: Long,
    )
}
