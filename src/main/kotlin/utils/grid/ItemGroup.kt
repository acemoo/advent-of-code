package utils.grid

data class ItemGroup<T: Item>(
    val type: String,
    val items: MutableList<T> = mutableListOf(), //TODO: can we make this immutable?
) {
    fun add(item: T) {
        items.add(item)
    }
}
