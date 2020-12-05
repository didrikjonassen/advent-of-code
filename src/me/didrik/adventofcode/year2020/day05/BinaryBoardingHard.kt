package me.didrik.adventofcode.year2020.day05

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day05/BinaryBoardingInput.txt")
    val input = file.readLines()
    val cabin = Array(128) { BooleanArray(8) }
    val seats = input.asSequence()
            .map { it.replace('F', '0') }
            .map { it.replace('B', '1') }
            .map { it.replace('L', '0') }
            .map { it.replace('R', '1') }
            .map { Pair(it.substring(0, 7).toInt(2), it.substring(7, 10).toInt(2)) }

    for (seat in seats) {
        cabin[seat.first][seat.second] = true
    }

    for (row in 0 until 128) {
        for (column in 1 until 7) {
            if (!cabin[row][column] && cabin[row][column - 1] && cabin[row][column + 1]) {
                println(row * 8 + column)
            }
        }
    }

}