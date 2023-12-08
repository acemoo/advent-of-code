package year_2023

import utils.math.findLeastCommonMultiple

class Day8: Day(8) {
    override fun solvePart1(input: List<String>) =
        Network.parseLines(input)
            .routeLength()

    override fun solvePart2(input: List<String>) =
        Network.parseLines(input)
            .ghostRouteLength()

    data class Network(
        val directions: List<Direction>,
        val nodes: Map<String, Node>,
    ) {
        companion object {
            fun parseLines(lines: List<String>) =
                Network(
                    lines.first().map(Direction::fromChar),
                    lines.drop(2)
                    .associate { line ->
                        val (name, left, right) = line.split(" = (", ", ", ")")
                        name to Node(name, left, right)
                    }
                )
        }

        fun routeLength() =
            calculateRoute().size()

        fun calculateRoute(): Route {
            var currentNode = nodes.getValue("AAA")
            val route = Route()
            while (true) {
                directions.forEach { direction ->
                    if (currentNode.name == "ZZZ") {
                        return route
                    }

                    val nextNodeName = currentNode.followDirection(direction)
                    route.addStep(Step(currentNode.name, nextNodeName))
                    currentNode = nodes.getValue(nextNodeName)
                }
            }
        }

        fun ghostRouteLength() =
            findLeastCommonMultiple(stepsTillZ())

        fun stepsTillZ() =
            nodes.filterKeys { it.endsWith("A") }
                .map { stepsTillZ(it.value) }

        private fun stepsTillZ(node: Node): Long {
            var currentNode = node
            var stepsTaken = 0L
            while (true) {
                directions.forEach { direction: Direction ->
                    if (currentNode.name.endsWith("Z")) {
                        return stepsTaken
                    }

                    val nextNodeName = currentNode.followDirection(direction)
                    currentNode = nodes.getValue(nextNodeName)
                    stepsTaken++
                }
            }
        }

    }

    data class Step(
        val from: String,
        val to: String,
    )

    data class Route(
        private var steps: MutableList<Step> = mutableListOf(),
    ) {
        fun addStep(step: Step) {
            steps.add(step)
        }

        fun size() =
            steps.size
    }

    enum class Direction {
        LEFT,
        RIGHT,
        ;

        companion object {
            fun fromChar(char: Char) =
                when (char) {
                    'L' -> LEFT
                    'R' -> RIGHT
                    else -> throw Exception("invalid instruction: $char")
                }
        }
    }

    data class Node(
        val name: String,
        val left: String,
        val right: String,
    ) {
        fun followDirection(direction: Direction) =
            when (direction) {
                Direction.LEFT -> left
                Direction.RIGHT -> right
            }
    }
}
