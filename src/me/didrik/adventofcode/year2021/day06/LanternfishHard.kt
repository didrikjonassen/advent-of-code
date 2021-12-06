package me.didrik.adventofcode.year2021.day06

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day06/Input.txt")
    val input = file.readLines().first().split(",").map { it.toInt() }
    val days = 256
    val timer = LongArray(9)
    for (i in input) {
        timer[i]++
    }

    repeat(days) {
        val temp = timer[0]
        for (i in 0 .. 7) {
            timer[i] = timer[i + 1]
        }
        timer[8] = temp
        timer[6] += temp
    }
    solve(timer.sum())
}