package utils.grid


open class Grid(
    val grid: Map<Location, Item>, //TODO: optimise by using a nested list
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


    interface GridBuilder {
        fun addItem(item: Item): GridBuilder

        fun build(): Grid
    }

    class Builder: GridBuilder {
        private val grid = mutableMapOf<Location, Item>()

        override fun addItem(item: Item) = apply {
            grid[item.location] = item
        }

        override fun build() =
            Grid(grid)
    }

    companion object {
        fun parseLines(lines: List<String>, builder: GridBuilder, mapper: (Char, Location) -> Item): Grid {
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
}
