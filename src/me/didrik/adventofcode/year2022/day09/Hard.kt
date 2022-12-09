package me.didrik.adventofcode.year2022.day09

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day09/Input.txt")
    val input = file.readLines()
    val knots = Array(10) {0 to 0}
    val tailPositions = mutableSetOf(knots[9])
    for (line in input) {
        val (dir, mag) = line.split(" ")
        repeat(mag.toInt()) {
            val head = knots[0]
            knots[0] = when (dir) {
                "R" -> head.first to head.second  + 1
                "U" -> head.first + 1 to head.second
                "D" -> head.first - 1 to head.second
                "L" -> head.first to head.second - 1
                else -> throw IllegalStateException()
            }
            for (i in 1 until knots.size) {
                knots[i] = newTail(knots[i - 1], knots[i])
            }
            tailPositions += knots[9]
        }
    }
    solve(tailPositions.size)
}

private fun newTail(head: Pair<Int, Int>, tail: Pair<Int, Int>): Pair<Int, Int> {
    if (max(abs(head.first - tail.first), abs(head.second - tail.second)) <= 1) {
        return tail
    }
    return tail.first + head.first.compareTo(tail.first) to tail.second + head.second.compareTo(tail.second)
}