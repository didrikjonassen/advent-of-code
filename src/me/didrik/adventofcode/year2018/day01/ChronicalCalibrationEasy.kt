package me.didrik.adventofcode.year2018.day01

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/me/didrik/adventofcode/year2018/day01/ChronicalCalibrationInput.txt")
    val input = file.readLines()
    val sum = input
            .map { it.toInt() }
            .sum()
    println("sum = $sum")
}