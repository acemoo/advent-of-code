package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import year_2023.Day7.*

class Day7Test:
AbstractDayTest(
    Day7(),
    6440,
    5905
) {
    private val input = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent().split("\n")

    private val hand = Hand(
        listOf(Card.THREE, Card.TWO, Card.TEN, Card.THREE, Card.KING),
        Kind.ONE_PAIR,
        765
    )

    private val jackHand = Hand(
        listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JACK, Card.FIVE),
        Kind.THREE_OF_A_KIND,
        684
    )

    private val jokerHand = Hand(
        listOf(Card.TEN, Card.FIVE, Card.FIVE, Card.JOKER, Card.FIVE),
        Kind.FOUR_OF_A_KIND,
        684
    )

    private val camelCardsJack = CamelCards(
        listOf(
            hand,
            jackHand,
            Hand(
                listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN),
                Kind.TWO_PAIR,
                28
            ),
            Hand(
                listOf(Card.KING, Card.TEN, Card.JACK, Card.JACK, Card.TEN),
                Kind.TWO_PAIR,
                220
            ),
            Hand(
                listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JACK, Card.ACE),
                Kind.THREE_OF_A_KIND,
                483
            ),
        )
    )

    private val camelCardsJoker = CamelCards(
        listOf(
            hand,
            jokerHand,
            Hand(
                listOf(Card.KING, Card.KING, Card.SIX, Card.SEVEN, Card.SEVEN),
                Kind.TWO_PAIR,
                28
            ),
            Hand(
                listOf(Card.KING, Card.TEN, Card.JOKER, Card.JOKER, Card.TEN),
                Kind.FOUR_OF_A_KIND,
                220
            ),
            Hand(
                listOf(Card.QUEEN, Card.QUEEN, Card.QUEEN, Card.JOKER, Card.ACE),
                Kind.FOUR_OF_A_KIND,
                483
            ),
        )
    )

    @Test
    fun parseJack() {
        assertThat(CamelCards.parseLines(input)).isEqualTo(camelCardsJack)
    }

    @Test
    fun parseJoker() {
        assertThat(CamelCards.parseLines(input, true)).isEqualTo(camelCardsJoker)
    }

    @TestFactory
    fun compareHandsByKind() =
        Kind.entries.flatMapIndexed { index: Int, kind: Kind ->
            Kind.entries.drop(index + 1).map { otherKind ->
                DynamicTest.dynamicTest("$kind vs $otherKind, winner $kind") {
                    val winningHand = Hand(listOf(), kind, 1)
                    val losingHand = Hand(listOf(), otherKind, 1)
                    assertThat(winningHand.vs(losingHand)).isEqualTo(Result.WIN)
                }
            } + DynamicTest.dynamicTest("$kind vs $kind, draw") {
                val hand = Hand(listOf(), kind, 1)
                assertThat(hand.vs(hand)).isEqualTo(Result.DRAW)
            }
        }

    @TestFactory
    fun cardsToKind() =
        listOf(
            listOf(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.ACE) to Kind.FIVE_OF_A_KIND,
            listOf(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.JOKER) to Kind.FIVE_OF_A_KIND,
            listOf(Card.ACE, Card.JOKER, Card.JOKER, Card.JOKER, Card.JOKER) to Kind.FIVE_OF_A_KIND,
            listOf(Card.ACE, Card.ACE, Card.ACE, Card.ACE, Card.JACK) to Kind.FOUR_OF_A_KIND,
            listOf(Card.ACE, Card.ACE, Card.ACE, Card.JOKER, Card.JACK) to Kind.FOUR_OF_A_KIND,
            listOf(Card.ACE, Card.ACE, Card.ACE, Card.KING, Card.KING) to Kind.FULL_HOUSE,
            listOf(Card.ACE, Card.ACE, Card.KING, Card.KING, Card.JOKER) to Kind.FULL_HOUSE,
            listOf(Card.ACE, Card.ACE, Card.ACE, Card.KING, Card.JACK) to Kind.THREE_OF_A_KIND,
            listOf(Card.ACE, Card.ACE, Card.KING, Card.KING, Card.JACK) to Kind.TWO_PAIR,
            listOf(Card.ACE, Card.ACE, Card.KING, Card.JACK, Card.QUEEN) to Kind.ONE_PAIR,
            listOf(Card.ACE, Card.KING, Card.JACK, Card.QUEEN, Card.TEN) to Kind.HIGH_CARD,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input should be $expected") {
                assertThat(Kind.fromCards(input)).isEqualTo(expected)
            }
        }


    @TestFactory
    fun compareHandsByCards() =
        Card.entries.flatMapIndexed { index: Int, card: Card ->
            Card.entries.drop(index + 1).map { otherCard ->
                DynamicTest.dynamicTest("$card vs $otherCard, winner $card") {
                    val winningHand = Hand(listOf(card), Kind.ONE_PAIR, 1)
                    val losingHand = Hand(listOf(otherCard), Kind.ONE_PAIR, 1)
                    assertThat(winningHand.vs(losingHand)).isEqualTo(Result.WIN)
                }
            } + DynamicTest.dynamicTest("$card vs $card, draw") {
                val hand = Hand(listOf(card), Kind.ONE_PAIR, 1)
                assertThat(hand.vs(hand)).isEqualTo(Result.DRAW)
            }
        }

    @Test
    fun rankedBids() {
        assertThat(camelCardsJack.rankedBids()).isEqualTo(
            listOf(
                1 to 765,
                2 to 220,
                3 to 28,
                4 to 684,
                5 to 483,
            )
        )
    }

    @Test
    fun totalWinnings() {
        assertThat(camelCardsJack.totalWinnings()).isEqualTo(6440)
    }
}
