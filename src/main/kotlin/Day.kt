
import java.io.File
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

abstract class Day(
    private val year: Int,
    private val day: Int
) {

    @OptIn(ExperimentalTime::class)
    fun solve(): Duration {
        val (result1, time1) = measureTimedValue {
            solvePart1(getInput())
        }
        val (result2, time2) = measureTimedValue {
            solvePart2(getInput())
        }
        printResult(year, day, result1, time1)
        printResult(year, day, result2, time2)
        return time1 + time2
    }

    @OptIn(ExperimentalTime::class)
    private fun printResult(year: Int, day: Int, result: Any, time: Duration) =
        println("$year  ${day.toString().padStart(2, '0')}.1:  ${result.toString().padEnd(50)} $time")

    abstract fun solvePart1(input: List<String>): Any
    abstract fun solvePart2(input: List<String>): Any

    fun getInput(): List<String> {
        val filename = "year_$year/input_day_$day.txt"
        val resource = {}::class.java.getResource(filename)
        if (resource?.file != null) {
            return File(resource.file).readLines()
        }
        throw Exception("File not found $filename")
    }
}
