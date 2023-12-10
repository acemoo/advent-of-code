package utils.grid

data class ItemGroup(
    val type: String,
    val items: MutableList<Item> = mutableListOf(), //TODO: can we make this immutable?
) {
    fun add(item: Item) {
        items.add(item)
    }
}
