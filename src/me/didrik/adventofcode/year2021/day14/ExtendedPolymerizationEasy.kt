package me.didrik.adventofcode.year2021.day14

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day14/Input.txt")
    val input = file.readLines()

    val template = input.first()
    val rules = input.drop(2).associate {
        val (from, to) = it.split(" -> ")
        from to to + from[1]
    }

    var polymer = template
    repeat(10) {
        polymer = polymer.windowed(2) {
            rules[it]!!
        }.joinToString(separator = "", prefix = polymer.first().toString())
    }
    val counts = IntArray(26)
    for (c in polymer) {
        counts[c - 'A']++
    }
    val max = counts.filterNot { it==0 }.maxOrNull()!!
    val min = counts.filterNot { it== 0 }.minOrNull()!!
    solve(max - min)
}