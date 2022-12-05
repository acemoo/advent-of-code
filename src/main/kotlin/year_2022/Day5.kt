package year_2022

class Day5: Day(5) {
    private val findCratesRegex = """((    )|[a-zA-Z])""".toRegex()
    private val findNumbersRegex = """\d+""".toRegex()

    override fun solvePart1(input: List<String>): String {
        val stacks = calculateStartingSituation(input)
        calculateInput(input)
            .forEach { input ->
                for(i in 0 until input[0]) {
                    moveOne(stacks, input[1], input[2])
                }
            }
        return stacks.map { it.last() }
            .joinToString(separator = "")
    }

    override fun solvePart2(input: List<String>): Any {
        val stacks = calculateStartingSituation(input)
        calculateInput(input)
            .forEach { input ->
                val pickup = (0 until input[0])
                    .map { stacks[input[1] - 1].removeLast() }
                    .reversed()
                stacks[input[2] - 1].addAll(pickup)
            }
        return stacks.map { it.last() }
            .joinToString(separator = "")
    }

    private fun calculateInput(input: List<String>) = input.dropWhile { it.isNotBlank() }
        .drop(1)
        .map { line ->
            findNumbersRegex.findAll(line)
                .map { it.value.toInt() }
                .toList()
        }

    private fun moveOne(stacks: List<Stack>, from: Int, to: Int) {
        val crate = stacks[from - 1].removeLast()
        stacks[to - 1].add(crate)
    }

    private fun calculateStartingSituation(input: List<String>): List<Stack> {
        val start = input.takeWhile { it.isNotBlank() }
            .reversed()
        val numberOfStacks = findNumbersRegex.findAll(start.first())
            .maxOf { it.value.toInt() }
        val stacks = (0 until numberOfStacks)
            .map { mutableListOf<Char>() }
        start.drop(1)
            .forEach { line ->
                findCratesRegex.findAll(line)
                    .map { it.value.first() }
                    .forEachIndexed { stackId, crate ->
                        if (crate.isLetter()) {
                            stacks[stackId].add(crate)
                        }
                    }
            }
        return stacks
    }

}

typealias Stack = MutableList<Char>
