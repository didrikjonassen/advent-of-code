package me.didrik.adventofcode.year2021.day08

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day08/Input.txt")
    val input = file.readLines()

    val lengths = listOf(2, 3, 4, 7)
    val count = input.flatMap {
        it.split(" | ")[1].trim().split(" ")
    }.count { it.length in lengths }
    solve(count)
}