package year_2022

class Day8: Day(8) {
    override fun solvePart1(input: List<String>) =
        Grid.from(input)
            .countVisibleTrees()

    override fun solvePart2(input: List<String>) =
        Grid.from(input)
            .calculateHighestScenicScore()

    data class Grid(
        private val trees: Map<Location, Tree>,
        private val maxX: Int,
        private val maxY: Int,
    ) {
        companion object {
            fun from(input: List<String>): Grid {
                var maxX = 0
                var maxY = 0
                val trees = mutableMapOf<Location, Tree>()
                input.forEachIndexed { row, line ->
                    line.forEachIndexed { column, char ->
                        trees[Location(row, column)] = Tree(char.digitToInt())
                        if (row > maxX) {
                            maxX = row
                        }
                        if (column > maxY) {
                            maxY = column
                        }
                    }
                }
                return Grid(trees, maxX, maxY)
            }
        }

        fun countVisibleTrees(): Int {
            var visibleTrees = 0
            for (x in 0 .. maxX) {
                for (y in 0 .. maxY) {
                    if (isVisibleFromOutside(Location(x, y))) {
                        visibleTrees++
                    }
                }
            }
            return visibleTrees
        }

        private fun isVisibleFromOutside(location: Location): Boolean {
            if (location.x == 0 || location.x == maxX
                || location.y == 0 || location.y == maxY) {
                return true
            }
            val tree = trees[location] ?: throw Exception("No tree on specified location")
            val hiddenFromLeft = (0 until location.x).map { x ->
                val other = trees[Location(x, location.y)] ?: throw Exception("No tree on specified location")
                other.blocksView(tree)
            }.any { it }
            val hiddenFromRight = (location.x + 1 .. maxX).map { x ->
                val other = trees[Location(x, location.y)] ?: throw Exception("No tree on specified location")
                other.blocksView(tree)
            }.any { it }
            val hiddenFromTop = (0 until location.y).map { y ->
                val other = trees[Location(location.x, y)] ?: throw Exception("No tree on specified location")
                other.blocksView(tree)
            }.any { it }
            val hiddenFromBottom = (location.y + 1 .. maxY).map { y ->
                val other = trees[Location(location.x, y)] ?: throw Exception("No tree on specified location")
                other.blocksView(tree)
            }.any { it }
            return !(hiddenFromLeft && hiddenFromRight && hiddenFromTop && hiddenFromBottom)
        }

        fun calculateHighestScenicScore(): Int {
            var highestScenicScore = 0
            for (x in 0 .. maxX) {
                for (y in 0 .. maxY) {
                    val scenicScore = calculateScenicScore(Location(x, y))
                    if (scenicScore > highestScenicScore) {
                        highestScenicScore = scenicScore
                    }
                }
            }
            return highestScenicScore
        }

        private fun calculateScenicScore(location: Location): Int {
            val tree = trees[location] ?: throw Exception("No tree on specified location")
            val scenicScoreNorth = if (location.x == 0) {
                0
            } else {
                var scenicScore = 0
                for (x in location.x - 1 downTo 0) {
                    val other = trees[Location(x, location.y)] ?: throw Exception("No tree on specified location")
                    scenicScore++
                    if (other.blocksView(tree)) {
                        break
                    }
                }
                scenicScore
            }
            val scenicScoreSouth = if (location.x == maxX) {
                0
            } else {
                var scenicScore = 0
                for (x in location.x + 1 .. maxX) {
                    val other = trees[Location(x, location.y)] ?: throw Exception("No tree on specified location")
                    scenicScore++
                    if (other.blocksView(tree)) {
                        break
                    }
                }
                scenicScore
            }
            val scenicScoreWest = if (location.y == 0) {
                0
            } else {
                var scenicScore = 0
                for (y in location.y - 1 downTo 0) {
                    val other = trees[Location(location.x, y)] ?: throw Exception("No tree on specified location")
                    scenicScore++
                    if (other.blocksView(tree)) {
                        break
                    }
                }
                scenicScore
            }
            val scenicScoreEast = if (location.y == maxY) {
                0
            } else {
                var scenicScore = 0
                for (y in location.y + 1 .. maxY) {
                    val other = trees[Location(location.x, y)] ?: throw Exception("No tree on specified location")
                    scenicScore++
                    if (other.blocksView(tree)) {
                        break
                    }
                }
                scenicScore
            }
            return scenicScoreNorth * scenicScoreSouth * scenicScoreWest * scenicScoreEast
        }
    }

    data class Location(
        val x: Int,
        val y: Int,
    )

    data class Tree(
        private val height: Int,
    ) {
        fun blocksView(other: Tree) =
            height >= other.height
    }
}
