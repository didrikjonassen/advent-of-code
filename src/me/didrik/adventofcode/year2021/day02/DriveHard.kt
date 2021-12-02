package me.didrik.adventofcode.year2021.day02

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day02/DriveInput.txt")
    val input = file.readLines()

    var aim = 0L
    var depth = 0L
    var forward = 0L
    for (line in input) {
        val (command, size) = line.split(" ")
        when (command) {
            "forward" -> {
                forward += size.toLong()
                depth += size.toLong() * aim
            }
            "down" -> aim += size.toLong()
            "up" -> aim -= size.toLong()
        }
    }
    solve(depth * forward)
}