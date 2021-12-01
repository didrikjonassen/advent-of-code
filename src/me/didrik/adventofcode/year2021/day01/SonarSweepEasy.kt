package me.didrik.adventofcode.year2021.day01

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day01/SonarSweepInput.txt")
    val input = file.readLines().map { it.toInt() }

    var count = 0
    for (i in 1 until input.size) {
        if (input[i] > input[i-1]) count++
    }
    solve(count)
}