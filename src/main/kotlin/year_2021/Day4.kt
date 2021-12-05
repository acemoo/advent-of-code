package year_2021

import groupConsecutiveBy

class Day4: Day(4) {
    override fun solvePart1(input: List<String>): Any {
        val numbers = input.first().split(",").map { it.toInt() }
        val boards = generateBoards(input)
        for (number in numbers) {
            for (board in boards) {
                board.markNumber(number)
                if (board.isWinner()) {
                    return board.getSumOfUnmarkedNumbers() * number
                }
            }
        }
        throw Exception("No winners!")
    }

    override fun solvePart2(input: List<String>): Any {
        val numbers = input.first().split(",").map { it.toInt() }
        val boards = generateBoards(input)
        val winningBoards = mutableListOf<Board>()
        for (number in numbers) {
            for (board in boards) {
                if (board.isWinner()) {
                    continue
                }
                board.markNumber(number)
                if (board.isWinner()) {
                    winningBoards.add(board)
                    if (winningBoards.size == boards.size) {
                        return board.getSumOfUnmarkedNumbers() * number
                    }
                }
            }
        }
        throw Exception("No winners!")
    }

    fun generateBoards(input: List<String>) =
        input
            .drop(2)
            .groupConsecutiveBy { it.isNotBlank() }
            .map { board ->
                Board(board.map { row ->
                    row
                        .trim()
                        .split("\\s+".toRegex())
                        .map(String::toInt)
                })
            }
}

data class Board(
    private val rows: List<List<Int>>,
) {
    private val board: List<MutableList<Number>> = rows
        .map { it.map { Number(it) }.toMutableList() }

    fun markNumber(calledNumber: Int) {
        for (rows in board) {
            for (cell in rows) {
                if (cell.number == calledNumber) {
                    cell.marked = true
                }
            }
        }
    }

    fun isWinner() =
        hasWinningColumn() || hasWinningRow()

    fun hasWinningRow() =
        board
            .any { row ->
                row.all { it.marked }
            }

    fun hasWinningColumn(): Boolean {
        for (row in board[0].indices) {
            val allMarked = board.indices.all { column ->
                board[column][row].marked
            }
            if (allMarked) {
                return true
            }
        }
        return false
    }

    fun getSumOfUnmarkedNumbers() =
        board.fold(0) { count, row ->
            count + row.fold(0) { acc, number ->
                acc + if (number.marked) 0 else number.number
            }
        }
}

data class Number(
    val number: Int,
    var marked: Boolean = false,
)
