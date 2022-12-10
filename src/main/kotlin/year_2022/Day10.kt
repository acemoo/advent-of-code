package year_2022

class Day10: Day(10) {
    override fun solvePart1(input: List<String>) =
        Processor().process(input)
            .sumOfSignals()

    override fun solvePart2(input: List<String>) =
        Processor().process(input)
            .renderCRT()

    class Processor {
        private var cycle = 0
        private var registerX = 1
        private val signals = mutableListOf<Int>()
        private val crt = CRT(40, 6)

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

        fun renderCRT() =
            crt.render()

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
            crt.draw(registerX)
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

    class CRT(
        private val width: Int,
        private val height: Int,
    ) {
        private var row = 0
        private var column = 0
        private val sprite = "###".padEnd(width, '.')

        private val pixels: List<MutableList<Char>> = buildList(height) {
            for (i in 0 until height) {
                add(mutableListOf())
            }
        }

        fun render() =
            pixels.map { it.joinToString("") }

        private fun getSpritePosition(move: Int) =
            if (move > 0) {
                sprite.takeLast(move) + sprite.dropLast(move)
            } else if (move < 0) {
                sprite.drop(-move) + sprite.take(-move)
            } else {
                sprite
            }

        fun draw(registerX: Int) {
            val spritePosition = getSpritePosition(registerX - 1)
            pixels[row].add(spritePosition[column])
            move()
        }

        private fun move() {
            column += 1
            if (column >= width) {
                column = 0
                row += 1
            }
        }
    }
}
