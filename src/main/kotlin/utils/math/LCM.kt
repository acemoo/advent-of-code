package utils.math

import kotlin.math.absoluteValue

private fun findLeastCommonMultiple(a: Long, b: Long): Long {
    if (a == 0L || b == 0L)
        return 0L

    val absoluteA = a.absoluteValue
    val absoluteB = b.absoluteValue

    return absoluteA / absoluteA.gcd(absoluteB) * absoluteB
}

fun findLeastCommonMultiple(numbers: List<Long>) =
    numbers.reduce { acc, i ->
        findLeastCommonMultiple(acc, i)
    }

private tailrec fun Long.gcd(other: Long): Long =
    if (other == 0L) {
        this
    } else {
        other.gcd(this % other)
    }
