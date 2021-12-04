package me.didrik.adventofcode.year2021.day03

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day03/BinaryDiagnosticInput.txt")
    val input = file.readLines()
    val bitCount = IntArray(input[0].length)

    for (line in input) {
        for (i in line.indices) {
            if (line[i] == '1') {
                bitCount[i]++
            }
        }
    }

    val gamma = bitCount.joinToString(separator = "") {
        when (it) {
            in 0..input.size / 2 -> "0"
            else -> "1"
        }
    }.toLong(2)
    val epsilon = (1 shl bitCount.size) - 1 - gamma
    solve(gamma * epsilon)
}