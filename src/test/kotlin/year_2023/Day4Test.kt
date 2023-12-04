package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class Day4Test:
    AbstractDayTest(
        Day4(),
        13,
        30
    ) {

        @TestFactory
        fun parseToCard() =
            listOf(
                "Card 1: 12 14 52 | 12 42 63" to Card(1, listOf(12, 14, 52), listOf(12, 42, 63)),
                "Card 2:  1  2 44 | 44 45 56" to Card(2, listOf(1, 2, 44), listOf(44, 45, 56)),
            ).map { (input, expected) ->
                DynamicTest.dynamicTest("$input should create card correctly") {
                    assertThat(Card.parseLine(input)).isEqualTo(expected)
            }
        }

    @TestFactory
        fun calculatePoints() =
            listOf(
                Card(1, listOf(1, 2, 3), listOf(1, 2, 3)) to 4,
                Card(2, listOf(10, 12, 40), listOf(1, 4, 10)) to 1,
                Card(3, listOf(12, 56, 78), listOf(10, 15, 40)) to 0,
            ).map { (input, expected) ->
                DynamicTest.dynamicTest("${input.winningNumbers}, ${input.yourNumbers} should be $expected points") {
                    assertThat(input.points()).isEqualTo(expected)
                }
            }

        @TestFactory
        fun matchingNumbers() =
            listOf(
                Card(1, listOf(1, 2, 3), listOf(1, 2, 3)) to setOf(1, 2, 3),
                Card(2, listOf(1, 4, 6, 7), listOf(1, 4, 5, 8)) to setOf(1, 4),
                Card(10, listOf(1, 5, 20, 40, 60), listOf(2, 5, 20, 40, 60)) to setOf(5, 20, 40, 60),
            ).map { (input, expected) ->
                DynamicTest.dynamicTest("winningNumbers ${input.winningNumbers}, yourNumbers ${input.yourNumbers} should match on $expected") {
                    assertThat(input.getMatchingNumbers()).isEqualTo(expected)
                }
            }

        @TestFactory
        fun winningCardsIds() =
            listOf(
                Card(1, listOf(1, 2, 3), listOf(1, 2, 3)) to listOf(2, 3, 4),
                Card(2, listOf(1, 2, 3), listOf(4, 5, 6)) to emptyList(),
                Card(3, listOf(1, 4, 56, 87, 99), listOf(1, 4, 56, 87, 99)) to listOf(4, 5, 6, 7, 8),
            ).map { (input, expected) ->
                DynamicTest.dynamicTest("$input should result in $expected") {
                    assertThat(input.getWinningCardIds()).isEqualTo(expected)
                }
            }

        @TestFactory
        fun winningShouldIncreaseCardCount() =
            listOf(
                Cards(mapOf(
                    1 to Card(1, listOf(1), listOf(1), 1),
                    2 to Card(2, listOf(1, 2, 3), listOf(4, 5, 6), 1),
                )) to listOf(1, 2),
                Cards(mapOf(
                    1 to Card(1, listOf(1), listOf(1), 2),
                    2 to Card(2, listOf(1, 2, 3), listOf(4, 5, 6), 1),
                )) to listOf(2, 3),
                Cards(mapOf(
                    1 to Card(1, listOf(1, 2, 3), listOf(1, 2, 3), 3),
                    2 to Card(2, listOf(1, 5), listOf(1, 6), 1),
                    3 to Card(3, listOf(1, 5), listOf(2, 6), 1),
                    4 to Card(4, listOf(1, 5), listOf(2, 6), 1),
                )) to listOf(3, 4, 8, 4)
            ).map { (input, expected) ->
                DynamicTest.dynamicTest("increase count") {
                    assertThat(
                        input.processCards()
                            .cards.values.map(Card::getCount)
                    ).isEqualTo(expected)
                }
            }
    }
