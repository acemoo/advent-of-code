package utils.grid

data class Location(
    val x: Int,
    val y: Int,
) {
    fun left() =
        Location(x, y - 1)

    fun right() =
        Location(x, y + 1)

    fun above() =
        Location(x - 1, y)

    fun aboveAndRight() =
        Location(x - 1, y + 1)

    fun aboveAndLeft() =
        Location(x - 1, y - 1)

    fun below() =
        Location(x + 1, y)

    fun belowAndRight() =
        Location(x + 1, y + 1)

    fun belowAndLeft() =
        Location(x + 1, y - 1)

    fun touches(otherLocation: Location) =
        otherLocation.x in x - 1 .. x + 1 &&
                otherLocation.y in y - 1 .. y + 1
}
