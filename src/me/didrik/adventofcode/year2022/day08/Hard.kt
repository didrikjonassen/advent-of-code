package me.didrik.adventofcode.year2022.day08

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day08/Input.txt")
    val input = file.readLines()

    var max = 0L
    val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    input[0].indices.forEach { x ->
        input.indices.forEach { y ->
            directions.map { scout(input, x to y, it, input[x][y], 0L) }
                .fold(1L, Math::multiplyExact)
                .also { if (it > max) max = it }
        }
    }
    solve(max)
}

private tailrec fun scout(grid: List<String>, pos: Pair<Int, Int>, dir: Pair<Int, Int>, maxHeight: Char, count: Long): Long {
    var (x, y) = pos
    val (dx, dy) = dir
    x += dx
    y += dy
    return if (x !in grid[0].indices || y !in grid.indices) {
        count
    } else if (grid[x][y] >= maxHeight) {
        count + 1
    } else {
        scout(grid, x to y, dir, maxHeight, count + 1)
    }
}
