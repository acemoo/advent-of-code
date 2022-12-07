package year_2015

class Day3: Day(3) {
    override fun solvePart1(input: List<String>): Int {
        val houses = Houses()
        val startingLocation = Location(0, 0)
        houses.visit(startingLocation)
        input.first()
            .map(::toDirection)
            .fold(startingLocation) { location, direction ->
                val newLocation = location.move(direction)
                houses.visit(newLocation)
                newLocation
            }
        return houses.getVisitedCount()
    }

    private fun toDirection(char: Char) = if (char == '>') {
        Direction.EAST
    } else if (char == '<') {
        Direction.WEST
    } else if (char == 'v') {
        Direction.SOUTH
    } else if (char == '^') {
        Direction.NORTH
    } else {
        throw Exception("Invalid direction")
    }

    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }

    class Houses {
        private val houses = mutableMapOf<Location, Int>()

        fun visit(location: Location) {
            houses.compute(location) { _, visits ->
                if (visits == null) {
                    1
                } else {
                    visits + 1
                }
            }
        }

        fun getVisitedCount() =
            houses.count()
    }

    data class Location(
        private val x: Int,
        private val y: Int,
    ) {
        fun move(direction: Direction) =
            when (direction) {
                Direction.NORTH -> Location(x + 1, y)
                Direction.SOUTH -> Location(x - 1, y)
                Direction.EAST -> Location(x, y + 1)
                Direction.WEST -> Location(x, y - 1)
            }
    }

    enum class Direction {
        NORTH,
        SOUTH,
        WEST,
        EAST,
        ;
    }
}
