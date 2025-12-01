package year_2025

class Day1: Day(1) {
    override fun solvePart1(input: List<String>): Int {
        val rotatingLock = RotatingLock(50)
        input.map { it.first() to it.drop(1).toInt() }
            .forEach { (direction, clicks) ->
                if (direction == 'L') {
                    rotatingLock.turnLeft(clicks)
                } else {
                    rotatingLock.turnRight(clicks)
                }
            }
        return rotatingLock.landedOnTimes[0]!!
    }

    override fun solvePart2(input: List<String>): Any {
        val rotatingLock = RotatingLock(50)
        input.map { it.first() to it.drop(1).toInt() }
            .forEach { (direction, clicks) ->
                if (direction == 'L') {
                    rotatingLock.turnLeft(clicks)
                } else {
                    rotatingLock.turnRight(clicks)
                }
            }
        return rotatingLock.totalTimesOn[0]!!
    }

    class RotatingLock(
        var position: Int,
    ) {
        val landedOnTimes = mutableMapOf<Int, Int>()
        val totalTimesOn = mutableMapOf<Int, Int>()

        fun turnLeft(clicks: Int) {
            repeat(clicks) {
                if (position == 0) {
                    position = 99
                } else {
                    position--
                }
                on()
            }

            landedOn()
        }

        fun turnRight(clicks: Int) {
            repeat(clicks) {
                if (position == 99) {
                    position = 0
                } else {
                    position++
                }
                on()
            }

            landedOn()
        }

        private fun landedOn() {
            landedOnTimes.compute(position) { _, count ->
                count?.plus(1) ?: 1
            }
        }

        private fun on() {
            totalTimesOn.compute(position) { _, count ->
                count?.plus(1) ?: 1
            }
        }
    }
}
