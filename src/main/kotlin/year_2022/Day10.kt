package year_2022

class Day10: Day(10) {
    override fun solvePart1(input: List<String>) =
        Processor()
            .process(input)

    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }

    class Processor {
        private var cycle = 0
        private var registerX = 1

        fun process(input: List<String>) =
            input.map { line ->
                if (line == "noop") {
                    Command.NOOP to 0
                } else {
                    Command.ADD_X to line.split(" ")[1].toInt()
                }
            }.mapNotNull { (command, num) ->
                when(command) {
                    Command.NOOP -> noOp()
                    Command.ADD_X -> addX(num)
                }
            }.sum()

        private fun isSignalCycle() =
            cycle == 20 ||
                    ((cycle - 20) % 40 == 0)

        private fun getSignal() =
            if (isSignalCycle()) {
                cycle * registerX
            } else {
                null
            }

        private fun noOp(): Int? {
            cycle += 1
            return getSignal()
        }

        private fun addX(num: Int): Int? {
            cycle += 1
            var signal = getSignal()
            cycle += 1
            signal = signal ?: getSignal()
            registerX += num
            return signal
        }
    }

    enum class Command {
        NOOP,
        ADD_X,
        ;
    }
}
