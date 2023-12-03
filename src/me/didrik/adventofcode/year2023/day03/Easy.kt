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
            grid[line][char] = input[line-1][char-1]
            grid[line][char] = input[line-1][char-1]
        }
    }

    var sum = 0
    for (line in 1..y) {
        var char = 1
        while (char <= x) {
            val (number, jump) = parseNumber(grid, line, char)
            sum += number
            char += jump
        }
    }
    solve(sum)
}

private val neighbours = (-1..1).flatMap { first -> (-1..1).map { second -> Pair(first, second) } }
    .filterNot { it.first == 0 && it.second == 0 }

private fun parseNumber(grid: Array<CharArray>, y: Int, x: Int): Pair<Int, Int> {
    if (!grid[y][x].isDigit()) {
        return 0 to 1
    }
    val digits = StringBuilder()
    var partNumber = false
    var dx = x
    while (grid[y][dx].isDigit()) {
        digits.append(grid[y][dx])
        val isPartNumber = neighbours.any { !grid[y + it.first][dx + it.second].isDigit() && grid[y + it.first][dx + it.second] != '.' }
        if (isPartNumber) {
            partNumber = true
        }
        dx++
    }
    val number = digits.toString()
    return if (partNumber) {
        number.toInt() to number.length
    } else {
        0 to number.length
    }
}