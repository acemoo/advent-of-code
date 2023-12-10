package utils.grid

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
