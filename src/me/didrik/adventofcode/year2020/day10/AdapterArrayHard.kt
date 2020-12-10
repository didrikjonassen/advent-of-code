package me.didrik.adventofcode.year2020.day10

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day10/AdapterArrayInput.txt")
    val input = file.readLines().map { it.toInt() }.toSet()
    val device = input.max()!! + 3
    val ways = LongArray(device + 1)
    if (1 in input) {
        ways[1]++
    }
    if (2 in input) {
        ways[2] = ways[1] + 1
    }
    if (3 in input) {
        ways[3] = ways[1] + ways[2] + 1
    }
    for (i in 4 until ways.size) {
        if (i in input) {
            ways[i] = ways[i-1] + ways[i-2] + ways[i-3]
        }
    }
    ways[device] = ways[device-1] + ways[device-2] + ways[device-3]
    println(ways[device])
}