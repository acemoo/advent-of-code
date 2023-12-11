package utils.grid

data class ItemGroups<T: Item>(
    val groups: MutableList<ItemGroup<T>> = mutableListOf(), //TODO: can we make this immutable?
) {
    fun add(item: T) {
        groups.last().add(item)
    }

    fun newGroup(type: String) {
        groups.add(ItemGroup(type, mutableListOf()))
    }
}
