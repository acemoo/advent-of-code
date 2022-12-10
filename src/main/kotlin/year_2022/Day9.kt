package year_2022

class Day9: Day(9) {
    override fun solvePart1(input: List<String>) =
        Rope(2)
            .process(input)
            .countVisitsByTail()

    override fun solvePart2(input: List<String>) =
        Rope(10)
            .process(input)
            .countVisitsByTail()

    class Rope(size: Int) {
        private val knots: List<Knot>
        private val head: Knot
        private val tail = Tail()

        init {
            knots = buildList {
                for (i in 1 until  size) {
                    add(Knot())
                }
                add(tail)
            }
            head = knots.first()
        }

        fun process(input: List<String>): Rope {
            input.forEach { line ->
                val (direction, distance) = getDirectionAndDistance(line)
                move(direction, distance)
            }
            return this
        }

        private fun getDirectionAndDistance(line: String): Pair<Direction, Int> {
            val (direction, distance) = line.split(" ")
            return when (direction) {
                "U" -> Direction.NORTH
                "R" -> Direction.EAST
                "D" -> Direction.SOUTH
                "L" -> Direction.WEST
                else -> throw Exception("Invalid direction")
            } to distance.toInt()
        }

        private fun move(direction: Direction, distance: Int) {
            for (i in 0 until distance) {
                move(direction)
            }
        }

        private fun move(direction: Direction) {
            head.move(direction)
            knots.windowed(2)
                .forEach { (knotA, knotB ) ->
                    knotB.catchUp(knotA)
                }
        }

        fun countVisitsByTail() =
            tail.countVisits()
    }

    data class Location(
        val x: Int,
        val y: Int,
    )

    enum class Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST,
        ;
    }

    open class Knot {
        var x = 0
        var y = 0

        fun move(direction: Direction) {
            when (direction) {
                Direction.NORTH -> y += 1
                Direction.EAST -> x += 1
                Direction.SOUTH -> y -= 1
                Direction.WEST -> x -= 1
            }
        }

        private fun getDirectionsTo(knot: Knot): List<Direction> {
            val directions = mutableListOf<Direction>()
            if (knot.x > x) {
                directions.add(Direction.EAST)
            }
            if (knot.x < x) {
                directions.add(Direction.WEST)
            }
            if (knot.y > y) {
                directions.add(Direction.NORTH)
            }
            if (knot.y < y) {
                directions.add(Direction.SOUTH)
            }
            return directions
        }

        private fun touches(knot: Knot) =
            (x - 1..x + 1).contains(knot.x) &&
                    (y - 1..y + 1).contains(knot.y)

        open fun catchUp(knot: Knot) {
            if (!touches(knot)) {
                getDirectionsTo(knot)
                    .forEach(::move)
            }
        }
    }

    class Tail: Knot() {
        private val visited = mutableMapOf(Location(0, 0) to 1)

        override fun catchUp(knot: Knot) {
            super.catchUp(knot)
            visit()
        }

        private fun visit() {
            val location = Location(x, y)
            visited.compute(location) { _, visits ->
                if (visits == null) {
                    1
                } else {
                    visits + 1
                }
            }
        }

        fun countVisits() =
            visited.count()
    }
}
