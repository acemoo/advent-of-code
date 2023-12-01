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

fun String.spelledDigitsToDigits() =
    windowed(5, 1, true) {
        return@windowed if (it.startsWith("one")) "1"
         else if (it.startsWith("two")) "2"
         else if (it.startsWith("three")) "3"
         else if (it.startsWith("four")) "4"
         else if (it.startsWith("five")) "5"
         else if (it.startsWith("six")) "6"
         else if (it.startsWith("seven")) "7"
         else if (it.startsWith("eight")) "8"
         else if (it.startsWith("nine")) "9"
        else it.first().toString()
    }.joinToString("")
