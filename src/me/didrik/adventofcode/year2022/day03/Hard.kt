package me.didrik.adventofcode.year2022.day03

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day03/Input.txt")
    val input = file.readLines()
    val result = input.windowed(3, 3) {
        it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet())
    }.sumOf {
        val char = it.single()
        if (char.isLowerCase()) {
            char - 'a' + 1
        } else {
            char - 'A' + 27
        }
    }
    solve(result)
}