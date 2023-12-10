package utils.grid

abstract class Item(
    val location: Location,
    val type: String // TODO: can we make this strongly typed?
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
}
