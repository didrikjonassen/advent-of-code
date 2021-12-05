package me.didrik.adventofcode.year2021.day05

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day05/HydrothermalVentureInput.txt")
    val input = file.readLines()

    val format = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
    val numbers = input.map {
        format.matchEntire(it)!!.destructured.toList().map { it.toInt() }
    }
    val maxValue = numbers.maxOf { it.maxOrNull()!! }
    val grid = Array(maxValue + 1) { IntArray(maxValue + 1) }

    for ((x1, y1, x2, y2) in numbers) {
        if (x1 == x2) {
            val (min, max) = listOf(y1, y2).sorted()
            for (y in min..max) {
                grid[x1][y]++
            }
        } else if (y1 == y2) {
            val (min, max) = listOf(x1, x2).sorted()
            for (x in min..max) {
                grid[x][y1]++
            }
        }
    }
    solve(grid.flatMap { it.toList() }.count { it >= 2 })
}