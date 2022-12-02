package year_2022

class Day2: Day(2) {
    override fun solvePart1(input: List<String>) =
        input.map(::toRound)
            .sumOf { it.getYourScore() }

    override fun solvePart2(input: List<String>) =
        input.map(::toRoundWithStrategy)
            .sumOf { it.getYourScore() }

    private fun toRound(line: String): Round {
        val (opponent, you) = line.split(" ")
            .map(::toHand)
        return Round(opponent, you)
    }

    private fun toRoundWithStrategy(line: String): Round {
        val (opponentInput, yourInput) = line.split(" ")
        val opponent = toHand(opponentInput)
        val you = toHandWithStrategy(opponent, getStrategy(yourInput))
        return Round(opponent, you)
    }

    private fun toHand(input: String) =
        if (arrayOf("A", "X").contains(input)) {
            Hand.ROCK
        } else if (arrayOf("B", "Y").contains(input)) {
            Hand.PAPER
        } else if (arrayOf("C", "Z").contains(input)) {
            Hand.SCISSORS
        } else {
            throw Exception("Invalid hand")
        }

    private fun toHandWithStrategy(opponent: Hand, strategy: Strategy) =
        when (strategy) {
            Strategy.WIN -> {
                when (opponent) {
                    Hand.ROCK -> Hand.PAPER
                    Hand.PAPER -> Hand.SCISSORS
                    Hand.SCISSORS -> Hand.ROCK
                }
            }
            Strategy.LOSE -> {
                when (opponent) {
                    Hand.ROCK -> Hand.SCISSORS
                    Hand.PAPER -> Hand.ROCK
                    Hand.SCISSORS -> Hand.PAPER
                }
            }
            Strategy.DRAW -> opponent
        }

    private fun getStrategy(input: String) =
        when (input) {
            "X" -> Strategy.LOSE
            "Y" -> Strategy.DRAW
            "Z" -> Strategy.WIN
            else -> throw Exception("Invalid input for strategy")
        }

    enum class Strategy {
        DRAW,
        WIN,
        LOSE,
        ;
    }

    class Round(
        private val opponent: Hand,
        private val you: Hand,
    ) {
        fun getYourScore() =
            you.points + you.getOutcome(opponent).points
    }

    enum class Hand(
        val points: Int,
    ) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3),
        ;

        fun getOutcome(versus: Hand) =
            when (this) {
                ROCK -> {
                    when (versus) {
                        ROCK -> Outcome.DRAW
                        PAPER -> Outcome.LOSE
                        SCISSORS -> Outcome.WIN
                    }
                }
                PAPER -> {
                    when (versus) {
                        ROCK -> Outcome.WIN
                        PAPER -> Outcome.DRAW
                        SCISSORS -> Outcome.LOSE
                    }
                }
                SCISSORS -> {
                    when (versus) {
                        ROCK -> Outcome.LOSE
                        PAPER -> Outcome.WIN
                        SCISSORS -> Outcome.DRAW
                    }
                }
            }
    }

    enum class Outcome(
        val points: Int,
    ) {
        WIN(6),
        DRAW(3),
        LOSE(0),
        ;
    }
}
