package me.didrik.adventofcode.year2022.day10

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day10/Input.txt")
    val input = file.readLines()
    val cycle = mutableListOf(0, 1)
    var x = 1
    input.forEach {
        if (it == "noop") {
            cycle += x
        } else {
            cycle += x
            x += it.substringAfter("addx ").toInt()
            cycle += x
        }
    }
    val result = (20..220 step 40).sumOf {
        cycle[it] * it
    }
    solve(result)
}