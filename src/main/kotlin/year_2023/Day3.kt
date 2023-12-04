package year_2023

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
            val grid = Grid.parseLines(lines)
            val numbers = mutableListOf<Number>()
            val symbols = mutableListOf<Symbol>()
            grid.groupedLines.forEach { itemGroups ->
                itemGroups.groups.forEach { itemGroup ->
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

data class ItemGroups(
    val groups: MutableList<ItemGroup> = mutableListOf(), //TODO: can we make this immutable?
) {
    fun add(item: Item) {
        groups.last().add(item)
    }

    fun newGroup(type: String) {
        groups.add(ItemGroup(type, mutableListOf()))
    }
}

data class ItemGroup(
    val type: String,
    val items: MutableList<Item> = mutableListOf(), //TODO: can we make this immutable?
) {
    fun add(item: Item) {
        items.add(item)
    }
}

data class Grid(
    val grid: Map<Location, Item>, //TODO: optimise by using a nested list
    val groupedLines: List<ItemGroups>,
) {
    fun touches(item: Item, type: String) =
        touchCount(item, type) > 0

    private fun touchCount(item: Item, type: String) =
        touchCountHorizontal(item, type) +
                touchCountVertical(item, type) +
                touchCountDiagonal(item, type)

    private fun touchCountHorizontal(item: Item, type: String) =
        countType(
            type,
            grid[item.left()],
            grid[item.right()],
        )

    private fun touchCountVertical(item: Item, type: String) =
        countType(
            type,
            grid[item.above()],
            grid[item.below()],
        )

    private fun touchCountDiagonal(item: Item, type: String) =
        countType(
            type,
            grid[item.aboveAndRight()],
            grid[item.aboveAndLeft()],
            grid[item.belowAndRight()],
            grid[item.belowAndLeft()],
        )

    private fun countType(type: String, vararg items: Item?) =
        items.count { it?.type == type }

    companion object {
        fun parseLines(lines: List<String>): Grid {
            val grid = mutableMapOf<Location, Item>()
            val groupedLines = mutableListOf<ItemGroups>()

            lines.forEachIndexed { x, line ->
                val groupsOnThisLine = ItemGroups()
                var previousType: String? = null
                line.forEachIndexed { y, c ->
                    val location = Location(x, y)
                    val item = when { //TODO: extract mapping character to Item to parameter for parseLines
                        c.isDigit() -> Digit(location, c.digitToInt())
                        c == '.' -> Dot(location)
                        else -> Symbol(location)
                    }
                    grid[location] = item
                    if (previousType != item.type) {
                        groupsOnThisLine.newGroup(item.type)
                    }
                    groupsOnThisLine.add(item)
                    previousType = item.type
                }
                groupedLines.add(groupsOnThisLine)
            }

            return Grid(
                grid,
                groupedLines,
            )
        }
    }
}

data class Location(
    val x: Int,
    val y: Int,
) {
    fun left() =
        Location(x, y - 1)

    fun right() =
        Location(x, y + 1)

    fun above() =
        Location(x - 1, y)

    fun aboveAndRight() =
        Location(x - 1, y + 1)

    fun aboveAndLeft() =
        Location(x - 1, y - 1)

    fun below() =
        Location(x + 1, y)

    fun belowAndRight() =
        Location(x + 1, y + 1)

    fun belowAndLeft() =
        Location(x + 1, y - 1)

    fun touches(otherLocation: Location) =
        otherLocation.x in x - 1 .. x + 1 &&
                otherLocation.y in y - 1 .. y + 1
}

sealed class Item(
    private val location: Location,
    val type: String // TODO: can we make this strongly typed? (probably by making the class non-sealed and typed: class Item<T>
) {
    fun touchesAny(items: List<Item>): Boolean {
        return items.any { it.touches(location) }
    }

    private fun touches(otherLocation: Location) =
        location.touches(otherLocation)

    fun left() =
        location.left()

    fun right() =
        location.right()

    fun above() =
        location.above()

    fun aboveAndRight() =
        location.aboveAndRight()

    fun aboveAndLeft() =
        location.aboveAndLeft()

    fun below() =
        location.below()

    fun belowAndRight() =
        location.belowAndRight()

    fun belowAndLeft() =
        location.belowAndLeft()

    override fun toString(): String {
        return "(x=${location.x}, y=${location.y})"
    }
}

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
