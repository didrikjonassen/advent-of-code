package me.didrik.adventofcode.year2021.day14

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day14/Input.txt")
    val input = file.readLines()

    val template = input.first()
    val rules = input.drop(2).associate {
        val (from, to) = it.split(" -> ")
        Pair(from[0], from[1]) to listOf(Pair(from[0], to[0]), Pair(to[0], from[1]))
    }

    var polymer = template.windowed(2).groupBy { it }.map {
        Pair(it.key[0], it.key[1]) to it.value.size.toLong()
    }.toMap()

    repeat(40) {
        polymer = polymer.flatMap { entry ->
            rules[entry.key]!!.map { it to entry.value }
        }.groupBy { it.first }
            .map { it.key to it.value.sumOf { it.second } }
            .toMap()
    }
    val counts = LongArray(26)
    for (c in polymer) {
        counts[c.key.first - 'A'] += c.value
        counts[c.key.second - 'A'] += c.value
    }
    counts[template.first() - 'A']++
    counts[template.last()  - 'A']++
    val max = counts.filterNot { it==0L }.maxOrNull()!!
    val min = counts.filterNot { it== 0L }.minOrNull()!!
    solve((max - min)/2)
}