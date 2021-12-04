package me.didrik.adventofcode.year2021.day04

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day04/GiantSquidInput.txt")
    val input = file.readLines()
    val numbers = input.first().split(",").map { it.toInt() }
    val boardText = input.drop(2).iterator()
    var boards = mutableListOf<Board2>()

    while (boardText.hasNext()) {
        val strings = boardText.asSequence().take(6).toList()
        boards += Board2(strings)
    }

    for (number in numbers) {
        for (board in boards) {
            board.mark(number)
            if (boards.size == 1 && board.solved()) {
                solve(number * board.sumUnsolved())
                return
            }
        }
        boards = boards.filterNot { it.solved() }.toMutableList()
    }
}

private class Board2(
    val grid: List<List<Int>>,
    val solved: Array<BooleanArray> = Array(5) { BooleanArray(5) }
) {
    constructor(text: List<String>) :
            this(text.filter { it.isNotEmpty() }
                .map { it.trim().replace("  ", " ").split(" ").map { it.toInt() } })


    fun mark(number: Int) {
        for (i in 0..4) {
            for (k in 0..4) {
                if (grid[i][k] == number) {
                    solved[i][k] = true
                    return
                }
            }
        }
    }

    fun solved(): Boolean {
        val rowSolved = solved.any { it.all { it } }
        val colSolved = solved.transpose().any { it.all { it } }
        return rowSolved || colSolved
    }

    fun sumUnsolved(): Int = grid.flatten()
        .zip(solved.flatMap { it.toList() })
        .filter {
            !it.second
        }.sumBy { it.first }
}

private fun Array<BooleanArray>.transpose() =
    (0..4).map { i ->
        this.map { it[i] }
    }