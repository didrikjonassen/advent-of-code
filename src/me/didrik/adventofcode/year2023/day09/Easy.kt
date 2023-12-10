package me.didrik.adventofcode.year2023.day09

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day09/Input.txt")
    val input = file.readLines()
    val sum = input.map { it.split(" ").map { it.toInt() } }
        .sumOf { nextNumber(it) }
    solve(sum)
}

private fun nextNumber(numbers: List<Int>): Int {
    return if (numbers.all { it == 0 }) {
        0
    } else {
        nextNumber(numbers.zipWithNext().map { it.second - it.first }) + numbers.last()
    }

}