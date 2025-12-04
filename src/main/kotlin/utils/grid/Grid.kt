package utils.grid

import kotlin.math.max


open class Grid<T: Item>(
    val grid: MutableMap<Location, T>, //TODO: optimise by using a nested list
    val maxX: Int,
    val maxY: Int,
) {
    fun touches(item: T, type: String) =
        touchCount(item, type) > 0

    fun touchCount(item: T, type: String) =
        touchCountHorizontal(item, type) +
                touchCountVertical(item, type) +
                touchCountDiagonal(item, type)

    private fun touchCountHorizontal(item: T, type: String) =
        countType(
            type,
            grid[item.left()],
            grid[item.right()],
        )

    private fun touchCountVertical(item: T, type: String) =
        countType(
            type,
            grid[item.above()],
            grid[item.below()],
        )

    private fun touchCountDiagonal(item: T, type: String) =
        countType(
            type,
            grid[item.aboveAndRight()],
            grid[item.aboveAndLeft()],
            grid[item.belowAndRight()],
            grid[item.belowAndLeft()],
        )

    fun getHorizontalOrVerticalSurroundingItems(item: T) =
        listOfNotNull(
            grid[item.above()],
            grid[item.below()],
            grid[item.left()],
            grid[item.right()],
        )

    private fun countType(type: String, vararg items: T?) =
        items.count { it?.type == type }


    interface GridBuilder<T: Item> {
        fun addItem(item: T): GridBuilder<T>

        fun build(): Grid<T>
    }

    class Builder<T: Item>: GridBuilder<T> {
        private val grid = mutableMapOf<Location, T>()
        private var maxX = 0
        private var maxY = 0

        override fun addItem(item: T) = apply {
            maxX = max(item.location.x, maxX)
            maxY = max(item.location.y, maxY)
            grid[item.location] = item
        }

        override fun build() =
            Grid<T>(grid, maxX, maxY)
    }

    companion object {
        fun <T: Item> parseLines(lines: List<String>, builder: GridBuilder<T>, mapper: (Char, Location) -> T): Grid<T> {
            lines.forEachIndexed { x, line ->
                line.forEachIndexed { y, char ->
                    builder.addItem(
                        mapper.invoke(char, Location(x, y))
                    )
                }
            }
            return builder.build()
        }
    }

    override fun toString() =
        buildString {
            (0 .. maxX ).forEach { x ->
                (0 .. maxY).forEach { y ->
                    val location = Location(x, y)
                    append((grid[location] ?: EmptyItem(location)).type)
                }
                this.appendLine()
            }
        }

    fun getItemOrNull(location: Location?) =
        grid[location]
}
