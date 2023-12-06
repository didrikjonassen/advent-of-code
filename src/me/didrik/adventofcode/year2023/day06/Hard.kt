package me.didrik.adventofcode.year2023.day06

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day06/Input.txt")
    val input = file.readLines()
    val time = input[0].split(":")[1].trim().replace(" ", "").toLong()
    val distance = input[1].split(":")[1].trim().replace(" ", "").toLong()

    // Finds shortest time to press negated
    val x = (1 until time).toList().binarySearch {
        if (it * (time - it) == distance) {
            0
        } else if (it * (time - it) < distance) {
            -1
        } else {
            1
        }
    }

    solve(time + x + x + 1)
}
