package year_2024

import kotlin.math.max
import kotlin.math.min

class Day1: Day(1) {
    override fun solvePart1(input: List<String>): Any {
        val leftList = input.map { it.split("   ").first().toInt() }.sorted()
        val rightList = input.map { it.split("   ").last().toInt() }.sorted()
        return leftList.foldIndexed(0) { i, acc, left ->
            val right = rightList[i]
            val high = max(right, left)
            val low = min(right, left)
            acc + high - low
        }
    }

    override fun solvePart2(input: List<String>): Any {
        val leftList = input.map { it.split("   ").first().toInt() }.sorted()
        val rightList = input.map { it.split("   ").last().toInt() }.sorted()
        return leftList.fold(0) { acc, left ->
            acc + left * rightList.count { it == left }
        }
    }
}
