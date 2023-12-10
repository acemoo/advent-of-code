package year_2023

import utils.grid.Grid
import utils.grid.GroupedGrid
import utils.grid.Item
import utils.grid.Location

class Day3 : Day(3) {
    override fun solvePart1(input: List<String>) =
        Schematic.parseLines(input)
            .getNumbersTouchingSymbols()
            .sumOf { it.intVal }

    override fun solvePart2(input: List<String>) =
        Schematic.parseLines(input)
            .getGearRatios()
            .sum()
}

data class Schematic(
    val grid: Grid,
    val numbers: List<Number>,
    val symbols: List<Symbol>,
) {
    companion object {
        fun parseLines(lines: List<String>): Schematic {
            val grid: GroupedGrid = Grid.parseLines(lines, GroupedGrid.Builder()) { char, location ->
                when {
                    char.isDigit() -> Digit(location, char.digitToInt())
                    char == '.' -> Dot(location)
                    else -> Symbol(location)
                }
            } as GroupedGrid

            val numbers = mutableListOf<Number>()
            val symbols = mutableListOf<Symbol>()
            grid.itemGroups.groups.forEach { itemGroup ->
                when (itemGroup.type) {
                    "digit" -> numbers.add(
                        Number(
                            itemGroup.items.map { it as Digit }
                                .fold(0) { acc: Int, digit: Digit ->
                                    acc * 10 + digit.intVal
                                },
                            itemGroup.items
                        )
                    )
                    "symbol" -> symbols.addAll(itemGroup.items.map { it as Symbol })
                }
            }
            return Schematic(grid, numbers, symbols)
        }
    }

    fun getNumbersTouchingSymbols() =
        numbers.filter { number ->
            number.items.any { grid.touches(it, "symbol") }
        }

    fun getGearRatios() =
        symbols.mapNotNull { symbol: Symbol ->
            val touchingNumbers = numbers.filter { number ->
                symbol.touchesAny(number.items)
            }

            if (touchingNumbers.size > 1) {
                touchingNumbers.fold(1) { acc, number ->
                    acc * number.intVal
                }
            } else {
                null
            }
        }
}

data class Number(
    val intVal: Int,
    val items: List<Item>,
)

class Digit(
    location: Location,
    val intVal: Int,
) : Item(location, "digit")

class Symbol(
    location: Location
) : Item(location, "symbol")

class Dot(
    location: Location
) : Item(location, "dot")
