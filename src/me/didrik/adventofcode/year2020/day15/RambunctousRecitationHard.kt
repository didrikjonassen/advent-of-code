package me.didrik.adventofcode.year2020.day15

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day15/RambunctousRecitationInput.txt")

    val input = file.readLines()[0].split(",").map { it.toInt() }
    val lastSpoken = IntArray(30000000)
    input.mapIndexed {index, number -> number to (index + 1) }.dropLast(1).forEach { lastSpoken[it.first] = it.second }
    var previousNumber = input.last()
    for (i in (input.size) until lastSpoken.size) {
        if (lastSpoken[previousNumber] == 0) {
            lastSpoken[previousNumber] = i
            previousNumber = 0
        } else {
            val temp = previousNumber
            previousNumber = i - lastSpoken[previousNumber]
            lastSpoken[temp] = i
        }
    }
    println(previousNumber)
}