package year_2023

class Day3 : Day(3) {
    override fun solvePart1(input: List<String>) =
        Schematic.parseLines(input)
            .getNumbersTouchingSymbols()
            .sumOf { it.intVal }

    override fun solvePart2(input: List<String>): Any {
        TODO("Not yet implemented")
    }
}

data class Schematic(
    val grid: Grid,
    val numbers: List<Number>
) {
    companion object {
        fun parseLines(lines: List<String>): Schematic {
            val grid = Grid.parseLines(lines)
            val numbers = grid.groupedLines.flatMap { itemGroups ->
                itemGroups.groups.mapNotNull { itemGroup ->
                    when (itemGroup.type) {
                        "digit" -> Number(
                            itemGroup.items.map { it as Digit }
                                .fold(0) { acc: Int, digit: Digit ->
                                    acc * 10 + digit.intVal
                                },
                            itemGroup.items
                        )
                        else -> null
                    }
                }
            }
            return Schematic(grid, numbers)
        }
    }

    fun getNumbersTouchingSymbols() =
        numbers.filter { number ->
            number.items.any { grid.touches(it, "symbol") }
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
        touchesHorizontal(item, type) ||
                touchesVertical(item, type) ||
                touchesDiagonal(item, type)

    private fun touchesHorizontal(item: Item, type: String) =
        grid[item.left()]?.type == type ||
                grid[item.right()]?.type == type

    private fun touchesVertical(item: Item, type: String) =
        grid[item.above()]?.type == type ||
                grid[item.below()]?.type == type

    private fun touchesDiagonal(item: Item, type: String) =
        grid[item.aboveAndRight()]?.type == type ||
                grid[item.aboveAndLeft()]?.type == type ||
                grid[item.belowAndRight()]?.type == type ||
                grid[item.belowAndLeft()]?.type == type
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
}

sealed class Item(
    private val location: Location,
    val type: String // TODO: can we make this strongly typed? (probably by making the class non-sealed and typed: class Item<T>
) {
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
