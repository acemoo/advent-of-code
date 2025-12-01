package year_2024

import kotlin.math.absoluteValue

class Day2: Day(2) {
    override fun solvePart1(input: List<String>) =
        input.map { line ->
            Report(
                line.split(" ").map { it.toInt() }
            )
        }.count { it.isSafe() }

    override fun solvePart2(input: List<String>) =
        input.map { line ->
            Report(
                line.split(" ").map { it.toInt() }
            )
        }.count { it.isSafe(true) }

    class Report(
        private val levels: List<Int>,
    ) {
        fun isSafe(enableDampener: Boolean = false): Boolean {
            var isIncreasing: Boolean? = null
            var previousLevel = levels[0]
            var dampenerAvailable = enableDampener
            levels.forEachIndexed { i, level ->
                if (i == 0)
                    return@forEachIndexed

                val difference = (level - previousLevel).absoluteValue
                if (difference > 3 || difference == 0) {
                    if (dampenerAvailable) {
                        dampenerAvailable = false
                        return@forEachIndexed
                    }
                    return false
                }
                if (isIncreasing == null) {
                    isIncreasing = previousLevel < level
                }

                if (
                    (isIncreasing == true && previousLevel > level) ||
                    (isIncreasing == false && previousLevel < level)
                ) {
                    if (dampenerAvailable) {
                        dampenerAvailable = false
                        return@forEachIndexed
                    }
                    return false
                }

                previousLevel = level
            }
            return true
        }
    }
}
