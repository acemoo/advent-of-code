package year_2022

import Day
import groupConsecutiveBy

class Day1(year: Int): Day(year, 1) {
    override fun solvePart1(input: List<String>) =
        input
            .groupConsecutiveBy { it.isNotBlank() }
            .maxOfOrNull { elfFoods ->
                elfFoods.sumOf { it.toInt() }
            } ?: 0

    override fun solvePart2(input: List<String>) =
        input
            .groupConsecutiveBy { it.isNotBlank() }
            .map { elfFoods ->
                elfFoods.sumOf { it.toInt() }
            }
            .sortedDescending()
            .take(3)
            .sum()
}
