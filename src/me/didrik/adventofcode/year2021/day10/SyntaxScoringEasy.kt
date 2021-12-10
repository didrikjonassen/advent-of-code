package me.didrik.adventofcode.year2021.day10

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day10/Input.txt")
    val input = file.readLines()
    solve(input.sumOf { it.corrupted() })
}

private val open = listOf('(', '[', '{', '<')
private val close = listOf(')', ']', '}', '>')
private val points = listOf(3L, 57L, 1197L, 25137L)

private fun String.corrupted(): Long {
    val chars = this.toMutableList()
    loop@
    while (chars.isNotEmpty()) {
        for (i in 0 until chars.size - 1) {
            if (chars[i] in open && chars[i + 1] in close) {
                if (open.indexOf(chars[i]) == close.indexOf(chars[i + 1])) {
                    chars.removeAt(i)
                    chars.removeAt(i)
                    continue@loop
                } else {
                    return points[close.indexOf(chars[i+1])]
                }
            }
        }
        return 0
    }
    return 0
}