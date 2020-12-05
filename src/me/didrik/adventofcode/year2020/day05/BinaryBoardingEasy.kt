package me.didrik.adventofcode.year2020.day05

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day05/BinaryBoardingInput.txt")
    val input = file.readLines()
    val maxId = input.asSequence()
            .map { it.replace('F', '0') }
            .map { it.replace('B', '1') }
            .map { it.replace('L', '0') }
            .map { it.replace('R', '1') }
            .map { Pair(it.substring(0, 7).toInt(2), it.substring(7, 10).toInt(2)) }
            .map { it.first * 8 + it.second }
            .max()
    println("maxId = $maxId")

}