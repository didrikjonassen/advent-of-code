package me.didrik.adventofcode.year2023.day03

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day03/Input.txt")
    val input = file.readLines()
    val y = input.size
    val x = input[0].length

    val grid = Array(y + 2) { CharArray(x + 2) { '.' } }
    for (line in 1..y) {
        for (char in 1..x) {
            grid[line][char] = input[line - 1][char - 1]
            grid[line][char] = input[line - 1][char - 1]
        }
    }

    val allGears = HashMap<Pair<Int, Int>, MutableList<Long>>()
    for (line in 1..y) {
        var char = 1
        while (char <= x) {
            val (number, jump, gears) = parseNumber(grid, line, char)
            char += jump
            gears.forEach {
                if (it in allGears) {
                    allGears[it]!! += number
                } else {
                    allGears[it] = mutableListOf(number)
                }
            }
        }
    }
    val sum = allGears.values.filter { it.size == 2 }
        .sumOf { it[0] * it[1] }
    solve(sum)
}

private val neighbours = (-1..1).flatMap { first -> (-1..1).map { second -> Pair(first, second) } }
    .filterNot { it.first == 0 && it.second == 0 }

private fun parseNumber(grid: Array<CharArray>, y: Int, x: Int): Triple<Long, Int, Set<Pair<Int, Int>>> {
    if (!grid[y][x].isDigit()) {
        return Triple(0, 1, emptySet())
    }
    val digits = StringBuilder()
    val allGears = HashSet<Pair<Int, Int>>()
    var dx = x
    while (grid[y][dx].isDigit()) {
        digits.append(grid[y][dx])
        val gears = neighbours.filter { grid[y + it.first][dx + it.second] == '*' }
        allGears += gears.map { y + it.first to dx + it.second }
        dx++
    }
    val number = digits.toString()
    return if (allGears.isNotEmpty()) {
        Triple(number.toLong(), number.length, allGears)
    } else {
        Triple(0, number.length, emptySet())
    }
}