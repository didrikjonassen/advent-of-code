package me.didrik.adventofcode.year2022.day03

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day03/Input.txt")
    val input = file.readLines()
    val result = input.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
        .map { it.first.toSet().intersect(it.second.toSet()) }.sumOf {
            val char = it.single()
            if (char.isLowerCase()) {
                char - 'a' + 1
            } else {
                char - 'A' + 27
            }
        }
    solve(result)
}