package me.didrik.adventofcode.year2020.day10

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day10/AdapterArrayInput.txt")
    val input = file.readLines().map { it.toInt() }.sorted()
    val device = input.last() + 3
    val ways = LongArray(device + 1)

    ways[0] = 1
    for (current in input + device) {
        ways[current] = (1..3).map { current - it }
                .filterNot { it < 0 }
                .map { ways[it] }
                .sum()
    }
    println(ways[device])
}