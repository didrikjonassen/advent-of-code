package me.didrik.adventofcode.year2021.day09

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day09/Input.txt")
    val input = file.readLines()

    val n = input.size
    val m = input[0].length
    val area = Array(n + 2) { IntArray(m + 2) { 10 } }

    for (i in 1..n) {
        for (k in 1..m) {
            area[i][k] = input[i - 1][k - 1].toString().toInt()
        }
    }

    var sum = 0L
    for (i in 1..n) {
        for (k in 1..m) {
            if (area[i][k] < listOf(area[i - 1][k], area[i + 1][k], area[i][k - 1], area[i][k + 1]).minOrNull()!!) {
                sum += area[i][k] + 1
            }
        }
    }
    solve(sum)
}