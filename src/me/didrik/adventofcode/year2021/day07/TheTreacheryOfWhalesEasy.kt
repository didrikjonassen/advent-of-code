package me.didrik.adventofcode.year2021.day07

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day07/Input.txt")
    val input = file.readLines().first().split(",").map { it.toInt() }

    val min = (0..input.maxOrNull()!!).minOfOrNull { i ->
        input.sumOf { (it - i).absoluteValue }
    }
    solve(min)
}