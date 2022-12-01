import year_2021.Year2021
import year_2022.Year2022

fun main() {
    listOf(
        Year2021(),
        Year2022(),
    )
        .forEach { year -> year.solve() }
}
