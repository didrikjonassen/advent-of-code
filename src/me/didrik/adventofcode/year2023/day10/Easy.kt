package me.didrik.adventofcode.year2023.day10

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day10/Input.txt")
    val input = file.readLines()
    var sy = 0
    var sx = 0
    val grid = input.mapIndexed { y, line ->
        line.mapIndexed { x, pipe ->
            when (pipe) {
                'F' -> listOf(y to x + 1, y + 1 to x)
                'J' -> listOf(y to x - 1, y - 1 to x)
                '|' -> listOf(y - 1 to x, y + 1 to x)
                '-' -> listOf(y to x + 1, y to x - 1)
                '7' -> listOf(y + 1 to x, y to x - 1)
                'L' -> listOf(y to x + 1, y - 1 to x)
                'S' -> {
                    sy = y
                    sx = x
                    listOf(y to x - 1, y - 1 to x) // Manual inspection of data
                }
                else -> listOf(-1 to -1)
            }
        }
    }

    val sPos = sy to sx
    var steps = 0
    var position = sPos
    var previous = grid[sy][sx].first()
    while (steps == 0 || position != sPos) {
        val tempPrevious = position
        position = (grid[position.first][position.second] - previous).first()
        previous = tempPrevious
        ++steps
    }
    solve(steps/2)
}
