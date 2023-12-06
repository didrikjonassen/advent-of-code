package me.didrik.adventofcode.year2023.day06

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day06/Input.txt")
    val input = file.readLines()
    val times = input[0].split(":")[1].trim().split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
    val distances = input[1].split(":")[1].trim().split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
    val ways = times.zip(distances).map { (time, distance) ->
        (1 until time).count {
            it * (time - it) > distance
        }.toLong()
    }.reduce(Long::times)
    solve(ways)
}
