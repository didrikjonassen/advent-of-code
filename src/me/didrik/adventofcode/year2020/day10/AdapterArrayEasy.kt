package me.didrik.adventofcode.year2020.day10

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day10/AdapterArrayInput.txt")
    val input = file.readLines().map { it.toInt() }.sorted()
    val currents = listOf(0) + input + (input.last() + 3)
    val diffs = IntArray(4)
    for (i in currents.subList(0, currents.size - 1).zip(currents.subList(1, currents.size))) {
        diffs[i.second - i.first]++
    }
    println(diffs[1] * diffs[3])

}