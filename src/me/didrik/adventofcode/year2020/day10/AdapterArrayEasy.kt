package me.didrik.adventofcode.year2020.day10

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day10/AdapterArrayInput.txt")
    val input = file.readLines().map { it.toInt() }.sorted()
    val device = input.last() + 3
    val diffs = IntArray(4)
    diffs[input.first() - 0]++
    for (i in 1 until input.size) {
        diffs[input[i] - input[i - 1]]++
    }
    diffs[device-input.last()]++
    println(diffs[1]*diffs[3])

}