import kotlin.time.Duration

class Year(
    private val year: Int
) {
    private val days = mutableListOf<Day>()

    fun add(day: Day) {
        days.add(day)
        days.sortBy { it.day }
    }

    fun solve() {
        val totalTime = days.map { day -> day.solve() }
            .fold(Duration.ZERO) { sum, element ->
                sum + element
            }
        println("".padEnd(72, '-'))
        println("$year total:  ${"".padEnd(46)} ${totalTime.toString().padStart(12)}")
        println()
        println()
    }
}
