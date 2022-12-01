package year_2021

import Year

class Year2021(
    year: Int = 2021,
): Year(
    listOf(
        Day1(year),
        Day2(year),
        Day3(year),
        Day4(year),
    )
)
