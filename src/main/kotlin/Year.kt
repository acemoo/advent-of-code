import kotlin.time.Duration

abstract class Year(
    private val days: Collection<Day>
) {
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
