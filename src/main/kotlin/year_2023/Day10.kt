package year_2023

import utils.grid.Grid
import utils.grid.Item
import utils.grid.Location

class Day10: Day(10) {
    override fun solvePart1(input: List<String>) =
        PipeMaze.parseLines(input)
            .halfPipeLength()

    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }

    class PipeMaze(
        private val grid: Grid<Pipe>,
        private val start: Pipe?,
    ) {

        fun getPipesConnectedToStart(): List<Pipe> =
            start?.let { startPipe ->
                grid.getHorizontalOrVerticalSurroundingItems(startPipe)
                    .filter { it.connectingLocations.contains(startPipe.location) }
            } ?: emptyList()

        override fun toString() =
            grid.toString()

        fun getOtherConnectedPipeOrNull(pipe: Pipe?, location: Location?) =
            grid.getItemOrNull(pipe?.getOtherConnectedLocationOrNull(location))

        fun findPiping(): List<Pipe> {
            val pipesConnectedToStart = getPipesConnectedToStart()
            val piping = mutableListOf(start)
            for (pipeConnectedToStart in pipesConnectedToStart) {
                piping.add(pipeConnectedToStart)
                var previousPipe = start
                var currentPipe: Pipe? = pipeConnectedToStart
                do {
                    val nextPipe = getOtherConnectedPipeOrNull(currentPipe, previousPipe?.location)
                    if (piping.contains(nextPipe)) {
                        return piping.filterNotNull()
                    }
                    piping.add(nextPipe)
                    previousPipe = currentPipe
                    currentPipe = nextPipe
                } while (nextPipe != null)
            }
            return piping.filterNotNull()
        }

        fun halfPipeLength() =
            findPiping().size / 2

        companion object {
            fun parseLines(lines: List<String>): PipeMaze {
                var start: Pipe? = null
                return PipeMaze(Grid.parseLines(lines, Grid.Builder()) { c: Char, location: Location ->
                    when (c) {
                        '|' -> Pipe(location, "‖", location.above(), location.below())
                        '-' -> Pipe(location, "═", location.left(), location.right())
                        'L' -> Pipe(location, "╚", location.above(), location.right())
                        'J' -> Pipe(location, "╝", location.left(), location.above())
                        '7' -> Pipe(location, "╗", location.left(), location.below())
                        'F' -> Pipe(location, "╔", location.right(), location.below())
                        'S' -> {
                            start = Pipe(location, "S")
                            start as Pipe
                        }
                        '.' -> Pipe(location, " ")
                        else -> throw Exception("No character $c defined.")
                    }
                }, start)
            }
        }
    }

    class Pipe(
        location: Location,
        type: String,
        vararg val connectingLocations: Location,
    ): Item(location, type) {
        override fun toString() =
            "'$type' $location"

        fun getOtherConnectedLocationOrNull(location: Location?) =
            connectingLocations.firstOrNull { it != location }
    }
}
