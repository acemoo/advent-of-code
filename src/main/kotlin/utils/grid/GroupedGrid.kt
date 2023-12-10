package utils.grid

class GroupedGrid(
    grid: Map<Location, Item>,
    val itemGroups: ItemGroups
): Grid(grid) {
    class Builder: GridBuilder {
        private val grid = mutableMapOf<Location, Item>()
        private val itemGroups = ItemGroups()
        private var previousType: String? = null

        override fun addItem(item: Item) = apply {
            grid[item.location] = item
            if (previousType != item.type) {
                itemGroups.newGroup(item.type)
                previousType = item.type
            }
            itemGroups.add(item)
        }

        override fun build(): Grid =
            GroupedGrid(grid, itemGroups)
    }
}
