package me.didrik.adventofcode.year2023.day02

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day02/Input.txt")
    val input = file.readLines()
    val result = input.map { it.split(":") }
        .filter { possible(it.last()) }
        .sumOf { it.first().split(" ").last().toInt() }
    solve(result)

}

private fun possible(draws: String): Boolean {
    return draws.split(",", ";")
        .map { it.trim() }
        .map { it.split(" ") }
        .all {
            val count = it.first().toInt()
            val colour = it.last()
            when (colour) {
                "red" -> count <= 12
                "green" -> count <= 13
                "blue" -> count <= 14
                else -> true
            }
        }
}