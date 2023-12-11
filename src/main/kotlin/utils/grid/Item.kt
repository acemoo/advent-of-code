package utils.grid

abstract class Item(
    val location: Location,
    val type: String, // TODO: can we make this strongly typed?
) {
    fun touchesAny(items: List<Item>): Boolean {
        return items.any { it.touches(location) }
    }

    private fun touches(otherLocation: Location) =
        location.touches(otherLocation)

    fun left() =
        location.left()

    fun right() =
        location.right()

    fun above() =
        location.above()

    fun aboveAndRight() =
        location.aboveAndRight()

    fun aboveAndLeft() =
        location.aboveAndLeft()

    fun below() =
        location.below()

    fun belowAndRight() =
        location.belowAndRight()

    fun belowAndLeft() =
        location.belowAndLeft()

    override fun toString(): String {
        return "(x=${location.x}, y=${location.y})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (location != other.location) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = location.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}

class EmptyItem(location: Location): Item(location, " ")
