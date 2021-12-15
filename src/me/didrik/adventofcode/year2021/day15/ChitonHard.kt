package me.didrik.adventofcode.year2021.day15

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day15/Input.txt")
    val input = file.readLines()

    val n = input.size
    val m = input[0].length
    val costs = Array(n * 5 + 2) { IntArray(m * 5 + 2) }
    val totalCost = Array(n * 5 + 2) { IntArray(m * 5 + 2) { Int.MAX_VALUE / 2 } }

    repeat(5) { i2 ->
        for (i in 0 until n) {
            repeat(5) { k2 ->
                for (k in 0 until m) {
                    costs[n * i2 + i + 1][m * k2 + k + 1] = (input[i][k] - '0' + i2 + k2).wrapAround()
                }
            }
        }
    }
    totalCost[1][1] = 0
    var changed = true
    while (changed) {
        changed = false
        for (i in 1..5 * n) {
            for (k in 1..5 * m) {
                listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).forEach { (x, y) ->
                    if (totalCost[i - x][k - y] + costs[i][k] < totalCost[i][k]) {
                        totalCost[i][k] = totalCost[i - x][k - y] + costs[i][k]
                        changed = true
                    }
                }
            }
        }
    }
    solve(totalCost[5*n][5*m])
}

private fun Int.wrapAround(): Int {
    return if (this > 9) {
        (this + 1) % 10
    } else {
        this
    }
}