package me.didrik.adventofcode.year2023.day02

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.max

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day02/Input.txt")
    val input = file.readLines()
    val result = input.map { it.split(":") }
        .sumOf { power(it.last()) }
    solve(result)

}

private fun power(draws: String): Long {
    var red = 0L
    var green = 0L
    var blue = 0L
    draws.split(",", ";")
        .map { it.trim() }
        .map { it.split(" ") }
        .forEach {
            val count = it.first().toLong()
            val colour = it.last()
            when (colour) {
                "red" -> red = max(red, count)
                "green" -> green = max(green, count)
                "blue" -> blue = max(blue, count)
            }
        }
    return red * green * blue
}
