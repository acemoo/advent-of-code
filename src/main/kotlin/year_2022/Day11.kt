package year_2022

class Day11: Day(11) {
    override fun solvePart1(input: List<String>) =
        Monkeys.from(input)
            .rounds(20)
            .multiplyMost2ActiveMonkeyInspections()


    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }

    class Monkeys(
        private val monkeys: List<Monkey>,
    ) {
        companion object {
            private val numberRegex = """\d+""".toRegex()
            private val operationRegex = """[*+]""".toRegex()

            private fun getOperator(line: String) =
                if (line == "  Operation: new = old * old") {
                    { old: Int, _: Int -> old * old }
                } else {
                    when (operationRegex.find(line)?.value) {
                        "*" -> { old: Int, num: Int -> old * num }
                        "+" -> { old: Int, num: Int -> old + num }
                        else -> throw Exception("couldn't find valid operator in line $line")
                    }
                }

            fun from(input: List<String>): Monkeys {
                val monkeys = mutableListOf<Monkey>()
                monkeys.addAll(input.chunked(7)
                    .mapIndexed { id, lines ->
                        val startingItems = numberRegex.findAll(lines[1]).map { it.value.toInt() }.toList()
                        val operator = getOperator(lines[2])
                        val operationNum = numberRegex.find(lines[2])?.value?.toInt()
                            ?: 0
                        val test = numberRegex.find(lines[3])?.value?.toInt()
                            ?: throw Exception("No test number found in line ${lines[3]}")
                        val monkeyTrue = numberRegex.find(lines[4])?.value?.toInt()
                            ?: throw Exception("No true monkey number found in ${lines[4]}")
                        val monkeyFalse = numberRegex.find(lines[5])?.value?.toInt()
                            ?: throw Exception("No false monkey number found in ${lines[5]}")
                        Monkey(id, startingItems, operator, operationNum, test, monkeyTrue, monkeyFalse, monkeys)
                    })
                return Monkeys(monkeys)
            }
        }

        fun rounds(num: Int): Monkeys {
            repeat(num) {
                monkeys.forEach(Monkey::turn)
                monkeys.forEach(::println)
            }
            return this
        }

        fun multiplyMost2ActiveMonkeyInspections() =
            monkeys.map { it.itemsInspected }
                .sortedDescending()
                .take(2)
                .fold(1) { total, num ->
                    total * num
                }
    }

    class Monkey(
        private val id: Int,
        startingItems: List<Int>,
        private val operator: (Int, Int) -> Int,
        private val operationNum: Int,
        private val testDivisible: Int,
        private val monkeyTrueId: Int,
        private val monkeyFalseId: Int,
        private val monkeys: List<Monkey>,
    ) {
        private val items = startingItems.toMutableList()
        var itemsInspected = 0

        fun turn() {
            while(items.isNotEmpty()) {
                val level = operator.invoke(items.removeFirst(), operationNum) / 3
                itemsInspected++
                if (level % testDivisible == 0) {
                    monkeys[monkeyTrueId].items.add(level)
                } else {
                    monkeys[monkeyFalseId].items.add(level)
                }
            }
        }

        override fun toString() =
            "Monkey $id: ${items.joinToString()}"
    }
}
