
import java.io.File
import kotlin.time.Duration
import kotlin.time.measureTimedValue

abstract class Day(
    private val year: Int,
    val day: Int
) {

    fun solve(): Duration {
        val (result1, time1) = measureTimedValue {
            solvePart1(getInput())
        }
        val (result2, time2) = measureTimedValue {
            try {
                solvePart2(getInput(2))
            } catch (e: NotImplementedError) {
                "NotImplemented"
            }
        }
        printResult(year, day, 1, result1, time1)
        printResult(year, day, 2, result2, time2)
        return time1 + time2
    }

    private fun printResult(year: Int, day: Int, part: Int, result: Any, time: Duration) {
        when (result) {
            is List<*> -> printListResult(year, day, part, result, time)
            else -> println("$year  ${day.toString().padStart(2, '0')}.$part:  ${result.toString().padEnd(46)} ${time.toString().padStart(12)}")
        }
    }

    private fun printListResult(year: Int, day: Int, part: Int, result: List<*>, time: Duration) {
        println("$year  ${day.toString().padStart(2, '0')}.$part:  ${"".padEnd(46)} ${time.toString().padStart(12)}")
        result.forEach(::println)
    }

    abstract fun solvePart1(input: List<String>): Any
    abstract fun solvePart2(input: List<String>): Any

    fun solvePart1(input: String) =
        solvePart1(listOf(input))

    fun solvePart2(input: String) =
        solvePart2(listOf(input))

    fun getInput(part: Int? = null): List<String> {
        if (part != null) {
            val resource = {}::class.java.getResource("year_$year/input_day_${day}_$part.txt")
            if (resource?.file != null) {
                return File(resource.file).readLines()
            }
        }
        val resource = {}::class.java.getResource("year_$year/input_day_$day.txt")
        if (resource?.file != null) {
            return File(resource.file).readLines()
        }
        throw Exception("File not found year: $year, day: $day, part: $part")
    }

    private fun StringBuilder.appendFractional(whole: Int, fractional: Int, fractionalSize: Int, unit: String, isoZeroes: Boolean) {
        append(whole)
        if (fractional != 0) {
            append('.')
            val fracString = fractional.toString().padStart(fractionalSize, '0')
            val nonZeroDigits = fracString.indexOfLast { it != '0' } + 1
            when {
                !isoZeroes && nonZeroDigits < 3 -> appendRange(fracString, 0, nonZeroDigits)
                else -> appendRange(fracString, 0, ((nonZeroDigits + 2) / 3) * 3)
            }
        }
        append(unit)
    }
}
