package me.didrik.adventofcode.year2022.day01

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day01/Input.txt")
    val input = file.readLines()
    val sums = mutableListOf(0)
    input.forEach {
        if (it.isEmpty()) {
            sums += 0
        } else {
            sums[sums.size - 1] += it.toInt()
        }
    }
    solve(sums.maxOrNull())
}