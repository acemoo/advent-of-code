package year_2025

class Day3: Day(3) {
    override fun solvePart1(input: List<String>) =
        input.sumOf {
            Bank(it)
                .findHighestJoltage(2)
        }

    override fun solvePart2(input: List<String>) =
        input.sumOf {
            Bank(it)
                .findHighestJoltage(12)
        }

    class Bank(val bank: String) {
        fun findHighestJoltage(num: Int): Long {
            return findHighestJoltageBatteries(num, bank.chunked(1).map { it.toInt() }).joinToString("").toLong()
        }

        fun findHighestJoltageBatteries(num: Int, bank: List<Int>, highestJoltageBatteries: MutableList<Int> = mutableListOf()): List<Int> {
            if (bank.size == num) {
                return highestJoltageBatteries + bank
            }
            if (num == 1) {
                highestJoltageBatteries.add(bank.max())
                return highestJoltageBatteries
            }
            val sectionToFindHighestNumber = bank.dropLast(num - 1)
            val highestNumber = sectionToFindHighestNumber.max()
            highestJoltageBatteries.add(highestNumber)
            val indexOfHighestNumber = sectionToFindHighestNumber.indexOf(highestNumber)
            return findHighestJoltageBatteries(num - 1, bank.drop(indexOfHighestNumber + 1), highestJoltageBatteries)
        }
    }
}
