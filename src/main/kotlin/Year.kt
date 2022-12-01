import kotlin.time.Duration
import kotlin.time.ExperimentalTime

abstract class Year(
    private val days: Collection<Day>
) {
    @OptIn(ExperimentalTime::class)
    fun solve() {
        val totalTime = days.map { day -> day.solve() }
            .fold(Duration.ZERO) { sum, element ->
                sum + element
            }
        println("".padEnd(70, '-'))
        println(totalTime.toString().padStart(70))
        println()
        println()
    }
}
