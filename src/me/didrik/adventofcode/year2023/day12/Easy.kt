package me.didrik.adventofcode.year2023.day12

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day12/Input.txt")
    val input = file.readLines()
        .map { it.split(" ") }
        .map { it.first() to it.last().split(",").map { it.toInt() } }
        .sumOf { arrangements(it.first, 0, it.second, 0, it.second.first()) + arrangements(it.first, 0, it.second, -1, 0) }
    solve(input)
}

private fun arrangements(springs: String, spring: Int, disabled: List<Int>, i: Int, remainingDisabled: Int): Int {
    if (remainingDisabled == 0 && i == disabled.size - 1) {
        return if (springs.substring(spring).none { it == '#' }) 1 else 0
    }
    if (spring >= springs.length) {
        return if (i > disabled.size) 1 else 0
    }
    if (remainingDisabled == 0 && springs[spring] == '#') {
        return 0
    }
    if (remainingDisabled != 0 && springs[spring] == '.') {
        return 0
    }
    if (remainingDisabled == 1 && springs[spring] in listOf('#', '?')) {
        return arrangements(springs, spring + 1, disabled, i, 0)
    }
    if (remainingDisabled != 0) {
        return arrangements(springs, spring + 1, disabled, i, remainingDisabled - 1)
    }
    return arrangements(springs, spring + 1, disabled, i, 0) + arrangements(springs, spring + 1, disabled, i + 1, disabled[i + 1])
}
