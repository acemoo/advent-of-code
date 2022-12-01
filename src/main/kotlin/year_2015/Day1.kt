package year_2015

class Day1: Day(1) {
    override fun solvePart1(input: List<String>): Int {
        val (up, down) = input.first()
            .partition { it == '(' }
        return up.length - down.length
    }

    override fun solvePart2(input: List<String>): Int {
        var floor = 0
        var position = 0
        input.first()
            .forEach {
                position++
                if (it == ')') {
                    if (floor == 0) {
                        return position
                    }
                    floor--
                } else if (it == '(') {
                    floor++
                }
            }
        throw Exception("Never entered the basement.")
    }
}
