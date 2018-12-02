package me.didrik.adventofcode.year2018.day01

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/me/didrik/adventofcode/year2018/day01/ChronicalCalibrationInput.txt")
    val input = file.readLines().map { it.toInt() }
    val set = mutableSetOf(0)
    var acc = 0
    loop@
    while (true) {
        for (line in input) {
            acc += line
            if (acc in set) break@loop
            else set += acc
        }
    }
    println("first duplicate = $acc")
}