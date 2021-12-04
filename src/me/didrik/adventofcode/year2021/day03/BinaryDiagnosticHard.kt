package me.didrik.adventofcode.year2021.day03

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day03/BinaryDiagnosticInput.txt")
    val input = file.readLines()
    val bits = input[0].length
    val numbers = input.map { it.toLong(2) }

    val oxygenRating = findOxygenGeneratorRating(numbers, 1L shl (bits - 1))
    val co2Rating = findCO2ScrubberRating(numbers, 1L shl (bits - 1))
    solve(oxygenRating * co2Rating)
}

private fun findOxygenGeneratorRating(numbers: List<Long>, bitMap: Long): Long {
    if (numbers.size == 1) {
        return numbers.first()
    }
    val (ones, zeroes) = numbers.partition { it and bitMap == bitMap }
    return if (ones.size >= zeroes.size) {
        findOxygenGeneratorRating(ones, bitMap shr 1)
    } else {
        findOxygenGeneratorRating(zeroes, bitMap shr 1)
    }
}

private fun findCO2ScrubberRating(numbers: List<Long>, bitMap: Long): Long {
    if (numbers.size == 1) {
        return numbers.first()
    }
    val (ones, zeroes) = numbers.partition { it and bitMap == bitMap }
    return if (zeroes.size <= ones.size) {
        findCO2ScrubberRating(zeroes, bitMap shr 1)
    } else {
        findCO2ScrubberRating(ones, bitMap shr 1)
    }
}