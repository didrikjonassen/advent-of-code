package me.didrik.adventofcode.year2023.day04

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day04/Input.txt")
    val input = file.readLines()
    val cardCount = LongArray(input.size) { 1L }
    input.map { it.split(": ")[1].split(" | ") }
        .map {
            it[0].split(" ").filter { it != "" } intersect it[1].split(" ").filter { it != "" }.toSet() }
        .map { it.count() }
        .forEachIndexed { index, matches ->
            for (i in 1..matches) {
                cardCount[index + i] += cardCount[index]
            }
        }
    solve(cardCount.sum())
}
