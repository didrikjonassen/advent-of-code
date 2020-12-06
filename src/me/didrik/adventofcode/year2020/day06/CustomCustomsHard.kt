package me.didrik.adventofcode.year2020.day06

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day06/CustomCustomsInput.txt")
    val input = file.readLines() + ""

    var sum = 0
    var currentGroup = "abcdefghijklmnopqrstuvwxyz".toCharArray().toSet()
    for (line in input) {
        if (line.isEmpty()) {
            sum += currentGroup.size
            currentGroup = "abcdefghijklmnopqrstuvwxyz".toCharArray().toSet()
        } else {
            currentGroup = currentGroup.intersect(line.toCharArray().toList())
        }
    }
    println("sum = $sum")
}