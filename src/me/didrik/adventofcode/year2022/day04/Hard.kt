package me.didrik.adventofcode.year2022.day04

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day04/Input.txt")
    val input = file.readLines()
    var counter = 0
    input.forEach {
        val (foo, bar) = it.split(",").map { cleaningRange(it) }
        if (foo.any { it in bar } || bar.any { it in foo })
            counter++
    }
    solve(counter)
}

private fun cleaningRange(s: String): IntRange {
    val (start, stop) = s.split("-")
    return IntRange(start.toInt(), stop.toInt())
}