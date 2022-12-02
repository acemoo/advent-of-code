
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
            try {
                solvePart2(getInput())
            } catch (e: NotImplementedError) {
                "NotImplemented"
            }
        }
        printResult(year, day, 1, result1, time1)
        printResult(year, day, 2, result2, time2)
        return time1 + time2
    }

    private fun printResult(year: Int, day: Int, part: Int, result: Any, time: Duration) {
        println("$year  ${day.toString().padStart(2, '0')}.$part:  ${result.toString().padEnd(46)} ${time.toString().padStart(12)}")
    }

    abstract fun solvePart1(input: List<String>): Any
    abstract fun solvePart2(input: List<String>): Any

    fun solvePart1(input: String) =
        solvePart1(listOf(input))

    fun solvePart2(input: String) =
        solvePart2(listOf(input))

    fun getInput(): List<String> {
        val filename = "year_$year/input_day_$day.txt"
        val resource = {}::class.java.getResource(filename)
        if (resource?.file != null) {
            return File(resource.file).readLines()
        }
        throw Exception("File not found $filename")
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
