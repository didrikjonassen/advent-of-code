package me.didrik.adventofcode.year2020.day06

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day06/CustomCustomsInput.txt")
    val input = file.readLines() + ""

    var sum = 0
    var currentGroup = setOf<Char>()
    for (line in input) {
        if (line.isEmpty()) {
            sum += currentGroup.size
            currentGroup = setOf()
        } else {
            currentGroup = currentGroup.union(line.toCharArray().toList())
        }
    }
    println("sum = $sum")
}