package year_2022

class Day10: Day(10) {
    override fun solvePart1(input: List<String>) =
        Processor().process(input)
            .sumOfSignals()

    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }

    class Processor {
        private var cycle = 0
        private var registerX = 1
        private val signals = mutableListOf<Int>()

        fun process(input: List<String>): Processor {
            input.map(::parseInput)
                .forEach { (command, num) ->
                    when (command) {
                        Command.NOOP -> noOp()
                        Command.ADD_X -> addX(num)
                    }
                }
            return this
        }

        private fun parseInput(line: String) =
            if (line == "noop") {
                Command.NOOP to 0
            } else {
                Command.ADD_X to line.split(" ")[1].toInt()
            }

        fun sumOfSignals() =
            signals.sum()

        private fun tick(times: Int) {
            for (i in 0 until times) {
                tick()
            }
        }

        private fun tick() {
            cycle += 1
            if (isSignalCycle()) {
                signals.add(getSignal())
            }
        }

        private fun isSignalCycle() =
            cycle == 20 ||
                    ((cycle - 20) % 40 == 0)

        private fun getSignal() =
                cycle * registerX

        private fun noOp() {
            tick(1)
        }

        private fun addX(num: Int) {
            tick(2)
            registerX += num
        }
    }

    enum class Command {
        NOOP,
        ADD_X,
        ;
    }
}
