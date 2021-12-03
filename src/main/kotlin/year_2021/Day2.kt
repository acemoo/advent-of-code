package year_2021

class Day2: Day(2) {
    override fun solvePart1(input: List<String>) =
        input
            .map { it.split(" ") }
            .map { it[0] to it[1].toInt() }
            .fold(0 to 0) { (horizontal, depth), (direction, amount) ->
                when (direction) {
                    "up" -> horizontal to depth - amount
                    "down" -> horizontal to depth + amount
                    "forward" -> horizontal + amount to depth
                    else -> throw Exception("Nope!")
                }
            }
            .run { first * second }

    override fun solvePart2(input: List<String>) =
        input
            .map { it.split(" ") }
            .map { it[0] to it[1].toInt() }
            .fold(Triple(0, 0, 0)) { (horizontal, depth, aim), (direction, amount) ->
                when (direction) {
                    "up" -> Triple(horizontal, depth, aim - amount)
                    "down" -> Triple(horizontal, depth, aim + amount)
                    "forward" -> Triple(horizontal + amount, depth + aim * amount, aim)
                    else -> throw Exception("Nope!")
                }
            }
            .run { first * second }
}
