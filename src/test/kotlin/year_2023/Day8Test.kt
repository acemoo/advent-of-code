package year_2023

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import year_2023.Day8.Direction
import year_2023.Day8.Network
import kotlin.test.Test

class Day8Test:
AbstractDayTest(
    Day8(),
    6,
    6L
) {
    private val inputSimple = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().split("\n")

    private val inputRepeat = """
        LLR

        AAA = (BBB, BBB)
        BBB = (AAA, ZZZ)
        ZZZ = (ZZZ, ZZZ)
    """.trimIndent().split("\n")

    private val ghostInput = """
        LR

        11A = (11B, XXX)
        11B = (XXX, 11Z)
        11Z = (11B, XXX)
        22A = (22B, XXX)
        22B = (22C, 22C)
        22C = (22Z, 22Z)
        22Z = (22B, 22B)
        XXX = (XXX, XXX)
    """.trimIndent().split("\n")

    private val networkSimple = Network(
        listOf(Direction.RIGHT, Direction.LEFT),
        mapOf(
            "AAA" to Day8.Node("AAA", "BBB", "CCC"),
            "BBB" to Day8.Node("BBB", "DDD", "EEE"),
            "CCC" to Day8.Node("CCC", "ZZZ", "GGG"),
            "DDD" to Day8.Node("DDD", "DDD", "DDD"),
            "EEE" to Day8.Node("EEE", "EEE", "EEE"),
            "GGG" to Day8.Node("GGG", "GGG", "GGG"),
            "ZZZ" to Day8.Node("ZZZ", "ZZZ", "ZZZ"),
        )
    )

    private val networkRepeat = Network(
        listOf(Direction.LEFT, Direction.LEFT, Direction.RIGHT),
        mapOf(
            "AAA" to Day8.Node("AAA", "BBB", "BBB"),
            "BBB" to Day8.Node("BBB", "AAA", "ZZZ"),
            "ZZZ" to Day8.Node("ZZZ", "ZZZ", "ZZZ"),
        )
    )

    private val ghostNetwork = Network(
        listOf(Direction.LEFT, Direction.RIGHT),
        mapOf(
            "11A" to Day8.Node("11A", "11B", "XXX"),
            "11B" to Day8.Node("11B", "XXX", "11Z"),
            "11Z" to Day8.Node("11Z", "11B", "XXX"),
            "22A" to Day8.Node("22A", "22B", "XXX"),
            "22B" to Day8.Node("22B", "22C", "22C"),
            "22C" to Day8.Node("22C", "22Z", "22Z"),
            "22Z" to Day8.Node("22Z", "22B", "22B"),
            "XXX" to Day8.Node("XXX", "XXX", "XXX"),
        )
    )

    @TestFactory
    fun parseNetwork() =
        listOf(
            inputSimple to networkSimple,
            inputRepeat to networkRepeat,
            ghostInput to ghostNetwork,
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("$input to $expected") {
                assertThat(Network.parseLines(input)).isEqualTo(expected)
            }
        }

    @TestFactory
    fun calculateRoute() =
        listOf(
            Triple("simple route", networkSimple, Day8.Route(mutableListOf(
                Day8.Step("AAA", "CCC"),
                Day8.Step("CCC", "ZZZ"),
            ))),
            Triple("repeat route", networkRepeat, Day8.Route(mutableListOf(
                Day8.Step("AAA", "BBB"),
                Day8.Step("BBB", "AAA"),
                Day8.Step("AAA", "BBB"),
                Day8.Step("BBB", "AAA"),
                Day8.Step("AAA", "BBB"),
                Day8.Step("BBB", "ZZZ"),
            ))),
        ).map { (name, input, expected) ->
            DynamicTest.dynamicTest(name) {
                assertThat(input.calculateRoute()).isEqualTo(expected)
            }
        }

    @Test
    fun countLoopsAndRemainderTillZ() {
        assertThat(ghostNetwork.stepsTillZ()).isEqualTo(listOf(2L, 3L))
    }

    @Test
    fun ghostRouteLength() {
        assertThat(ghostNetwork.ghostRouteLength()).isEqualTo(6)
    }
}
