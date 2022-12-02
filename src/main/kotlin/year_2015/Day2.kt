package year_2015

class Day2: Day(2) {
    override fun solvePart1(input: List<String>) =
        input.sumOf { line ->
            val (length, width, height) = line
                .split("x")
                .map { it.toInt() }
            Dimensions(length, width, height).getPaperNeeded()
        }

    override fun solvePart2(input: List<String>) =
        input.sumOf { line ->
            val (length, width, height) = line
                .split("x")
                .map { it.toInt() }
            Dimensions(length, width, height).getRibbonNeeded()
        }

    data class Dimensions(
        private val length: Int,
        private val width: Int,
        private val height: Int,
    ) {
        private val lengthWidthSurface = 2 * length * width
        private val widthHeightSurface = 2 * width * height
        private val heightLengthSurface = 2 * height * length

        private val lengthWidthPerimeter = 2 * (length + width)
        private val widthHeightPerimeter = 2 * (width + height)
        private val heightLengthPerimeter = 2 * (height + length)

        fun getPaperNeeded() =
            lengthWidthSurface + widthHeightSurface + heightLengthSurface + ( getSmallest(lengthWidthSurface, widthHeightSurface, heightLengthSurface) / 2)

        private fun getSmallest(x: Int, y: Int, z: Int): Int {
            var smallest = x
            if (y < smallest) {
                smallest = y
            }
            if (z < smallest) {
                smallest = z
            }
            return smallest
        }

        fun getRibbonNeeded() =
            getCubicFeet() + getSmallest(lengthWidthPerimeter, widthHeightPerimeter, heightLengthPerimeter)

        private fun getCubicFeet() =
            length * width * height
    }
}
