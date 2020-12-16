package me.didrik.adventofcode.year2020.day16

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day16/TicketTranslationInput.txt")
    val input = file.readLines()

    val ranges = input.takeWhile { it.isNotEmpty() }
            .map { """\D+(\d+)-(\d+) or (\d+).(\d+)""".toRegex().matchEntire(it)!!.destructured }
            .map { (first, second, third, fourth) -> listOf(first.toLong()..second.toLong(), third.toLong()..fourth.toLong()) }

    val fieldNames = input.takeWhile { it.isNotEmpty() }
            .map { it.substringBefore(':') }

    val myTicket = input.dropWhile { it != "your ticket:" }
            .drop(1)
            .first()
            .split(",")
            .map { it.toLong() }

    val validOtherTickets = input.dropWhile { it != "nearby tickets:" }
            .drop(1)
            .map { row -> row.split(",").map { it.toLong() } }
            .filter { row -> row.all { it in ranges.flatten() } }

    val fieldToPosition = IntArray(fieldNames.size) { -1 }

    while (-1 in fieldToPosition) {
        for (i in fieldNames.indices) {
            val possiblePositions = (myTicket.indices).map { k ->
                if (k in fieldToPosition) {
                    return@map -1
                }
                val allMatch = (validOtherTickets + listOf(myTicket)).map { it[k] }
                        .all { it in ranges[i] }
                if (allMatch) {
                    k
                } else {
                    -1
                }
            }.filter { it > -1 }
            if (possiblePositions.size == 1) {
                fieldToPosition[i] = possiblePositions[0]
            }
        }
    }

    val answer = fieldNames.mapIndexed { index, fieldname -> Pair(index, fieldname) }
            .filter { it.second.indexOf("departure") == 0 }
            .map { myTicket[fieldToPosition[it.first]] }
            .reduce(Long::times)

    solve(answer)
}

private operator fun List<LongRange>.contains(i: Long): Boolean {
    for (range in this) {
        if (i in range) {
            return true
        }
    }
    return false
}