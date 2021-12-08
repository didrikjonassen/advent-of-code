package me.didrik.adventofcode.year2021.day08

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day08/Input.txt")
    val input = file.readLines()

    var sum = 0L
    for (line in input) {
        val (left, right) = line.split(" | ").map { it.trim() }
        val sortedDigits = left.split(" ").sortedBy { it.length }.map { it.toList().sorted().joinToString(separator = "") }
        val digit =  Array(10) {""}
        digit[1] = sortedDigits[0]
        digit[7] = sortedDigits[1]
        digit[4] = sortedDigits[2]
        digit[8] = sortedDigits[9]
        val fiveSegments = sortedDigits.subList(3, 6)
        digit[3] = fiveSegments.first { it.toSet().containsAll(digit[1].toSet()) }
        val topLeft = digit[4].toSet().subtract(digit[3].toSet()).first()
        digit[5] = fiveSegments.first { it != digit[3] && it.contains(topLeft) }
        digit[2] = fiveSegments.first { it != digit[3] && it != digit[5] }
        val sixSegments = sortedDigits.subList(6, 9)
        val middle = digit[2].toSet().intersect(digit[5].toSet()).intersect(digit[4].toSet()).first()
        digit[0] = sixSegments.first { middle !in it}
        digit[9] = sixSegments.first { it != digit[0] && it.toSet().containsAll(digit[1].toSet()) }
        digit[6] = sixSegments.first { it != digit[0] && it != digit[9] }

        var subSum = 0L
        for (d in right.trim().split(" ").map { it.toList().sorted().joinToString(separator = "") }) {
            subSum *= 10
            subSum += digit.indexOf(d)
        }
        sum += subSum
    }
    solve(sum)
}