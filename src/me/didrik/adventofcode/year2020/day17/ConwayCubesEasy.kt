package me.didrik.adventofcode.year2020.day17

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day17/ConwayCubesInput.txt")
    val input = file.readLines()

    val cycles = 6
    val padding = cycles + 1
    val gridSize = input.size + padding * 2
    val smallGridSize = 1 + padding * 2
    val grid = Array(2) { Array(smallGridSize) { Array(gridSize) { BooleanArray(gridSize) } } }

    for (i in input.indices) {
        for (k in input.indices) {
            grid[0][padding][padding + i][padding + k] = input[i][k] == '#'
        }
    }

    var set = 0

    val neighbours = (-1..1).flatMap { first -> (-1..1).flatMap { second -> (-1..1).map { third -> Triple(first, second, third) } } }
            .filterNot { it.first == 0 && it.second == 0 && it.third == 0 }

    repeat(cycles) {
        for (i in 1 until (smallGridSize - 1)) {
            for (k in 1 until (gridSize - 1)) {
                for (m in 1 until (gridSize - 1)) {
                    val activeNeighbours = neighbours.map { grid[set][i + it.first][k + it.second][m + it.third] }
                            .filter { it }
                            .count()
                    grid[set xor 1][i][k][m] = when {
                        activeNeighbours == 3 -> true
                        activeNeighbours == 2 && grid[set][i][k][m] -> true
                        else -> false
                    }
                }
            }
        }
        set = set xor 1
    }

    solve(grid[set].flatten().flatMap { it.toList() }.filter { it }.count())

}