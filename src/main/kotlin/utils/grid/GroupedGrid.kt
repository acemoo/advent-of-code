package utils.grid

import kotlin.math.max

class GroupedGrid<T: Item>(
    grid: Map<Location, T>,
    maxX: Int,
    maxY: Int,
    val itemGroups: ItemGroups<T>
): Grid<T>(grid, maxX, maxY) {
    class Builder<T: Item>: GridBuilder<T> {
        private val grid = mutableMapOf<Location, T>()
        private var maxX = 0
        private var maxY = 0
        private val itemGroups = ItemGroups<T>()
        private var previousType: String? = null

        override fun addItem(item: T) = apply {
            grid[item.location] = item
            maxX = max(maxX, item.location.x)
            maxY = max(maxY, item.location.y)
            if (previousType != item.type) {
                itemGroups.newGroup(item.type)
                previousType = item.type
            }
            itemGroups.add(item)
        }

        override fun build(): Grid<T> =
            GroupedGrid(grid, maxX, maxY, itemGroups)
    }
}
