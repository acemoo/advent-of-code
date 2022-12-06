import kotlin.time.Duration

class Year {
    private val days = mutableListOf<Day>()

    fun add(day: Day) {
        days.add(day)
    }

    fun solve() {
        val totalTime = days.map { day -> day.solve() }
            .fold(Duration.ZERO) { sum, element ->
                sum + element
            }
        println("".padEnd(72, '-'))
        println(totalTime.toString().padStart(72))
        println()
        println()
    }
}
