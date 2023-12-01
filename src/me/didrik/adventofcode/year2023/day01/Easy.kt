package me.didrik.adventofcode.year2023.day01

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day01/Input.txt")
    val input = file.readLines()
    val solution = input.map { it.filter { it.isDigit() } }.map { "${it.first()}${it.last()}".toInt() }.sum()

    solve(solution)
}