fun <T> Iterable<T>.groupConsecutiveBy(predicate: (T) -> Boolean) : List<List<T>> {
    var leftToGroup = this.toList()
    val groups = mutableListOf<List<T>>()

    while (leftToGroup.isNotEmpty()) {
        val dropped = leftToGroup
            .takeWhile { !predicate(it) }
        val newGroup = leftToGroup
                .takeWhile(predicate)
        if (newGroup.isNotEmpty()) {
            groups.add(newGroup)
        }
        leftToGroup = leftToGroup.subList(newGroup.size + dropped.size, leftToGroup.size)
    }
    return groups
}
