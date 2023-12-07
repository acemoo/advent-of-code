package year_2023

import kotlin.math.max

class Day7: Day(7) {
    override fun solvePart1(input: List<String>) =
        CamelCards.parseLines(input)
            .totalWinnings()

    override fun solvePart2(input: List<String>) =
        CamelCards.parseLines(input, true)
            .totalWinnings()

    data class CamelCards(
        val hands: List<Hand>,
    ) {
        companion object {
            fun parseLines(lines: List<String>, jAsJoker: Boolean = false): CamelCards {
                return CamelCards(lines.map { line ->
                    val (cardsAsStrings, bid) = line.split(" ")
                    val cards = if (jAsJoker) {
                        cardsAsStrings.map(Card::fromJokerChar)
                    } else {
                        cardsAsStrings.map(Card::fromChar)
                    }
                    Hand(
                        cards,
                        Kind.fromCards(cards),
                        bid.toInt(),
                    )
                })
            }
        }

        fun rankedBids() =
            hands.sorted()
                .mapIndexed { index, hand ->
                    index + 1 to hand.bid
                }

        fun totalWinnings() =
            rankedBids().sumOf {
                it.first * it.second
            }
    }

    data class Hand(
        val cards: List<Card>,
        val kind: Kind,
        val bid: Int,
    ) : Comparable<Hand> {

        fun vs(other: Hand): Result {
            val kindCompare = kind.compareTo(other.kind)
            return when {
                kindCompare < 0 -> Result.WIN
                kindCompare > 0 -> Result.LOSE
                else -> {
                    val cardVsOtherCard = cards.mapIndexedNotNull { index, card ->
                        val otherCard = other.cards[index]
                        if (card == otherCard) {
                            null
                        } else {
                            card to otherCard
                        }
                    }.firstOrNull()
                    val cardsResult = cardVsOtherCard?.first?.compareTo(cardVsOtherCard.second) ?: 0
                    when {
                        cardsResult < 0 -> Result.WIN
                        cardsResult > 0 -> Result.LOSE
                        else -> Result.DRAW
                    }
                }
            }
        }

        override fun compareTo(other: Hand) =
            when (vs(other)) {
                Result.WIN -> 1
                Result.LOSE -> -1
                Result.DRAW -> 0
            }
    }

    enum class Card: Comparable<Card> {
        ACE,
        KING,
        QUEEN,
        JACK,
        TEN,
        NINE,
        EIGHT,
        SEVEN,
        SIX,
        FIVE,
        FOUR,
        THREE,
        TWO,
        JOKER,
        ;

        companion object {

            fun fromJokerChar(char: Char) =
                if (char == 'J') {
                    JOKER
                } else
                    fromChar(char)

            fun fromChar(char: Char) =
                when (char) {
                    'A' -> ACE
                    'K' -> KING
                    'Q' -> QUEEN
                    'J' -> JACK
                    'T' -> TEN
                    '9' -> NINE
                    '8' -> EIGHT
                    '7' -> SEVEN
                    '6' -> SIX
                    '5' -> FIVE
                    '4' -> FOUR
                    '3' -> THREE
                    '2' -> TWO
                    else -> throw Exception("No Card with char $char")
                }
        }
    }

    enum class Kind {
        FIVE_OF_A_KIND,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        THREE_OF_A_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD,
        ;

        companion object {
            fun fromCards(cards: List<Card>): Kind {
                val eachCount = cards.groupingBy { it }.eachCount()
                var jokers = 0
                var maxOfAKind = 0
                var pairs = 0
                eachCount.forEach { (card, count) ->
                    if (card == Card.JOKER) {
                        jokers = count
                    } else {
                        maxOfAKind = max(maxOfAKind, count)
                        if (count > 1) {
                            pairs++
                        }
                    }
                }
                return when {
                    maxOfAKind == 5 -> FIVE_OF_A_KIND
                    maxOfAKind + jokers == 5 -> FIVE_OF_A_KIND
                    maxOfAKind == 4 -> FOUR_OF_A_KIND
                    maxOfAKind + jokers == 4 -> FOUR_OF_A_KIND
                    maxOfAKind > 2 && pairs > 1 -> FULL_HOUSE
                    maxOfAKind + jokers > 2 && pairs > 1 -> FULL_HOUSE
                    maxOfAKind > 2 && jokers > 0 -> FULL_HOUSE
                    maxOfAKind == 3 -> THREE_OF_A_KIND
                    maxOfAKind + jokers == 3 -> THREE_OF_A_KIND
                    pairs > 1 -> TWO_PAIR
                    pairs == 1 && jokers > 0 -> TWO_PAIR
                    pairs == 1 -> ONE_PAIR
                    jokers > 0 -> ONE_PAIR
                    else -> HIGH_CARD
                }
            }
        }
    }

    enum class Result {
        WIN,
        LOSE,
        DRAW,
        ;
    }
}
