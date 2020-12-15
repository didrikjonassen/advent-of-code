package me.didrik.adventofcode.year2020.day15

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day15/RambunctousRecitationInput.txt")

    val input = file.readLines()[0].split(",").map { it.toInt() }
    val spoken = input.mapIndexed {index, number -> number to (index + 1) }.dropLast(1).toMap().toMutableMap()
    var previousNumber = input.last()
    for (i in (input.size) until 30000000) {
        if (previousNumber !in spoken) {
            spoken[previousNumber] = i
            previousNumber = 0
        } else {
            val temp = previousNumber
            previousNumber = i - spoken[previousNumber]!!
            spoken[temp] = i
        }
    }
    println(previousNumber)
}