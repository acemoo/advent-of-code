package year_2023

class Day9: Day(9) {
    override fun solvePart1(input: List<String>) =
        Report.parse(input)
            .sumOfNextValues()

    override fun solvePart2(input: List<String>) =
        Report.parse(input)
            .sumOfPreviousValues()

    data class Report(
        val valueHistories: List<History>
    ) {
        fun sumOfNextValues() =
            valueHistories.sumOf(History::extrapolateNextValue)

        fun sumOfPreviousValues() =
            valueHistories.sumOf(History::extrapolatePreviousValue)

        companion object {
            fun parse(lines: List<String>) =
                Report(
                    lines.map(History::parse)
                )
        }
    }

    data class History(
        val values: List<Int>
    ) {

        private fun getSteps(list: List<Int>) =
            list.windowed(2)
                .map { it[1] - it[0] }
                .toMutableList()

        fun calculateLastSteps(): List<Int> {
            val lastSteps = mutableListOf(values.last())
            var steps = values
            while (steps.any { it != 0 }) {
                steps = getSteps(steps)
                lastSteps.add(steps.last())
            }
            return lastSteps
        }

        fun calculateFirstSteps(): List<Int> {
            val firstSteps = mutableListOf(values.first())
            var steps = values
            while (steps.any { it != 0 }) {
                steps = getSteps(steps)
                firstSteps.add(steps.first())
            }
            return firstSteps
        }

        fun extrapolateNextValue() =
            calculateLastSteps().sum()

        fun extrapolatePreviousValue() =
            calculateFirstSteps().reduceRight { acc, i ->
                acc - i
            }

        companion object {
            fun parse(line: String) =
                History(
                    line.split(" ")
                        .map(String::toInt)
                )

            fun of(vararg values: Int) =
                History(values.toList())
        }
    }
}
