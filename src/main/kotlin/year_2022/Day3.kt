package year_2022

class Day3: Day(3) {
    override fun solvePart1(input: List<String>) =
        input.sumOf { line ->
            val compartmentSize = line.length / 2
            val compartment1 = line.subSequence(0..compartmentSize)
            val compartment2 = line.subSequence(compartmentSize until line.length)
            getPriority(compartment1.first { compartment2.contains(it) })
        }

    private fun getPriority(item: Char) =
        if (item.isUpperCase()) {
            item.code.minus(38)
        } else {
            item.code.minus(96)
        }

    override fun solvePart2(input: List<String>) =
        input.chunked(3)
            .sumOf { rucksacks ->
                val a = rucksacks.reduce { acc, s ->
                    acc.filter { s.contains(it) }
                }
                getPriority(a.first())
            }
}
