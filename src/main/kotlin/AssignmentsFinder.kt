import java.io.File

class AssignmentsFinder {
    private val findYearAndDayRegex = """(\d+)""".toRegex()

    fun getYears(): Collection<Year> {
        val years = mutableMapOf<Int, Year>()
        val relativeRoot = File("src/main/kotlin")
        relativeRoot.walk().forEach { file ->
            val relativeFile = file.relativeTo(relativeRoot)
            if (file.isFile && relativeFile.path.startsWith("year_") && relativeFile.name != "Day.kt") {
                val (year, day) = findYearAndDayRegex.findAll(relativeFile.path)
                    .map { it.value.toInt() }
                    .toList()
                years.computeIfAbsent(year) { Year() }
                    .add(Class.forName("year_$year.Day$day").kotlin.constructors.first().call() as Day)
            }
        }
        return years.toSortedMap().values
    }
}
