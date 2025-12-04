package year_2025

import utils.grid.Grid
import utils.grid.Item
import utils.grid.Location

class Day4: Day(4) {
    override fun solvePart1(input: List<String>) =
        PrintingDepartment(input)
            .findAccessibleRollsOfPaper()
            .count()

    override fun solvePart2(input: List<String>) =
        PrintingDepartment(input)
            .removeAndCountAllAccessibleRollsOfPaper()
            .removedRollsOfPaper

    class RollOfPaper(location: Location): Item(location, "@")

    class PrintingDepartment(input: List<String>) {
        var removedRollsOfPaper = 0
        val floor = Grid.parseLines(input, Grid.Builder()) { c: Char, location: Location ->
            when (c) {
                '@' -> RollOfPaper(location)
                '.' -> null
                else -> throw IllegalArgumentException("Invalid character '$c'")
            }
        }

        fun findAccessibleRollsOfPaper() =
             floor.grid.filter { (_, item) ->
                 floor.touchCount(item, "@") < 4
            }

        fun removeAndCountAllAccessibleRollsOfPaper(): PrintingDepartment {
            var accessibleRollsOfPaper = findAccessibleRollsOfPaper()
            while (accessibleRollsOfPaper.isNotEmpty()) {
                accessibleRollsOfPaper.forEach {
                    removedRollsOfPaper++
                    floor.grid.remove(it.key)
                }
                accessibleRollsOfPaper = findAccessibleRollsOfPaper()
            }
            return this
        }
    }
}
