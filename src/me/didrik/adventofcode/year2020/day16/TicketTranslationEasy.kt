package me.didrik.adventofcode.year2020.day16

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day16/TicketTranslationInput.txt")
    val input = file.readLines()

    val ranges = input.takeWhile { it.isNotEmpty() }
            .map { """\D+(\d+)-(\d+) or (\d+).(\d+)""".toRegex().matchEntire(it)!!.destructured }
            .flatMap { (first, second, third, fourth) -> listOf(first.toInt()..second.toInt(), third.toInt()..fourth.toInt()) }

    val answer = input.dropWhile { it != "nearby tickets:" }
            .drop(1)
            .flatMap { row -> row.split(",").map { it.toInt() } }
            .filter { it !in ranges }
            .sum()

    solve(answer)

}

private operator fun List<IntRange>.contains(i: Int): Boolean {
    for (range in this) {
        if (i in range) {
            return true
        }
    }
    return false
}