package year_2023

class Day4: Day(4) {
    override fun solvePart1(input: List<String>) =
        input.map(Card::parseLine)
            .map(Card::points)
            .sum()

    override fun solvePart2(input: List<String>) =
        Cards.parseLines(input)
            .processCards()
            .count()
}

data class Cards(
    val cards: Map<Int, Card>,
) {
    companion object {
        fun parseLines(lines: List<String>) =
            Cards(
                lines.mapIndexed { index, line ->
                    (index + 1) to Card.parseLine(line)
                }.toMap()
            )
    }

    fun processCards(): Cards {
        cards.keys
            .forEach { id ->
                processCard(id)
            }
        return this
    }

    fun processCard(id: Int) {
        val card = cards.getValue(id)
        card.getWinningCardIds()
            .forEach { gainingId ->
                cards.getValue(gainingId).gain(card.getCount())
            }
    }

    fun count() =
        cards.values.map(Card::getCount)
            .sum()

}

data class Card(
    val id: Int,
    val winningNumbers: List<Int>,
    val yourNumbers: List<Int>,
    private var count: Int = 1
) {

    companion object {
        fun parseLine(line: String): Card {
            val numbers = line.substring(5).split(":", "|")
                .map {
                    it.trim().split(" ")
                        .filter(String::isNotBlank)
                        .map {
                            it.toInt()
                        }
                }

            return Card(
                numbers[0][0],
                numbers[1],
                numbers[2]
            )
        }
    }

    fun points(): Int {
        val matchingNumbers = getMatchingNumbers()
        if (matchingNumbers.isEmpty()) {
            return 0
        } else if (matchingNumbers.size == 1) {
            return 1
        }
        return matchingNumbers.drop(1)
            .fold(1) { acc: Int, _: Int ->
                acc * 2
            }
    }

    fun getMatchingNumbers() =
        winningNumbers.intersect(yourNumbers.toSet())

    fun getWinningCardIds() =
        List(getMatchingNumbers().size) { index ->
            id + index + 1
        }

    fun gain(amount: Int) {
        count += amount
    }

    fun getCount() =
        count
}
