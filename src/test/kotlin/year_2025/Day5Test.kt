package year_2025

import AbstractDayTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test:
    AbstractDayTest(
        Day5(),
        3,
        14L
    ){

    @Test
    fun `toNonOverlapRanges 1-10`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(1, 10),
        ))).containsExactlyInAnyOrder(
            LongRange(1, 10)
        )
    }

    @Test
    fun `toNonOverlapRanges 1-10, 5-15`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(1, 10),
            LongRange(5, 15)
        ))).containsExactlyInAnyOrder(
            LongRange(1, 15)
        )
    }

    @Test
    fun `toNonOverlapRanges 1-15, 20-25`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(1, 10),
            LongRange(5, 15),
            LongRange(20, 25)
        ))).containsExactlyInAnyOrder(
            LongRange(1, 15),
            LongRange(20, 25),
        )
    }

    @Test
    fun `toNonOverlapRanges 1-10, 20-25`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(1, 10),
            LongRange(20, 25)
        ))).containsExactlyInAnyOrder(
            LongRange(1, 10),
            LongRange(20, 25),
        )
    }

    @Test
    fun `toNonOverlapRanges 10-14, 12-18`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(10, 14),
            LongRange(12, 18)
        ))).containsExactlyInAnyOrder(
            LongRange(10, 18),
        )
    }

    @Test
    fun `toNonOverlapRanges 3-5, 10-14`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(3, 5),
            LongRange(10, 14)
        ))).containsExactlyInAnyOrder(
            LongRange(3, 5),
            LongRange(10, 14),
        )
    }

    @Test
    fun `toNonOverlapRanges 3-5, 10-14, 16-20`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(3, 5),
            LongRange(10, 14),
            LongRange(16, 20),
        ))).containsExactlyInAnyOrder(
            LongRange(3, 5),
            LongRange(10, 14),
            LongRange(16, 20),
        )
    }

    @Test
    fun `toNonOverlapRanges 16-20, 12-18`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(16, 20),
            LongRange(12, 18),
        ))).containsExactlyInAnyOrder(
            LongRange(12, 20),
        )
    }

    @Test
    fun `toNonOverlapRanges 10-14, 16-20, 12-18`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(10, 14),
            LongRange(16, 20),
            LongRange(12, 18),
        ))).containsExactlyInAnyOrder(
            LongRange(10, 20),
        )
    }

    @Test
    fun `toNonOverlapRanges 3-5, 10-14, 16-20, 12-18`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(3, 5),
            LongRange(10, 14),
            LongRange(16, 20),
            LongRange(12, 18),
        ))).containsExactlyInAnyOrder(
            LongRange(3, 5),
            LongRange(10, 20),
        )
    }

    @Test
    fun `toNonOverlapRanges 3-5, 6-8`() {
        val cafetariaInventoryManagementSystem = Day5.CafetariaInventoryManagementSystem(listOf("1-2"))
        assertThat(cafetariaInventoryManagementSystem.toNonOverlappingRanges(listOf(
            LongRange(3, 5),
            LongRange(6, 8),
        ))).containsExactlyInAnyOrder(
            LongRange(3, 8),
        )
    }
}

/*
3-5
10-14
16-20
12-18
 */
