package year_2021

import java.io.File

abstract class Day(
    private val day: Int
) {
    fun solve() {
        println("Day $day")
        println("Part 1: ${solvePart1(getInput())}")
        println("Part 2: ${solvePart2(getInput())}")
    }

    abstract fun solvePart1(input: List<String>): Any
    abstract fun solvePart2(input: List<String>): Any

    fun getInput(): List<String> {
        val filename = "input_day_$day.txt"
        val resource = {}::class.java.getResource(filename)
        if (resource?.file != null) {
            return File(resource.file).readLines()
        }
        throw Exception("File not found $filename")
    }
}
