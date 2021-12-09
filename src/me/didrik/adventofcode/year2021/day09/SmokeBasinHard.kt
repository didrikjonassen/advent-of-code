package me.didrik.adventofcode.year2021.day09

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day09/Input.txt")
    val input = file.readLines()

    val n = input.size
    val m = input[0].length
    val area = Array(n + 2) { IntArray(m + 2) { 10 } }
    val visited = Array(n + 2) { BooleanArray(m + 2) }

    for (i in 1..n) {
        for (k in 1..m) {
            area[i][k] = input[i - 1][k - 1].toString().toInt()
        }
    }

    fun explore(x: Int, y: Int): Int {
        if (visited[x][y]) return 0
        if (area[x][y] > 8) return 0

        visited[x][y] = true
        return 1 + explore(x + 1, y) + explore(x - 1, y) + explore(x, y + 1) + explore(x, y - 1)
    }

    val basinSizes = mutableListOf<Long>()
    for (i in 1..n) {
        for (k in 1..m) {
            if (area[i][k] < listOf(area[i - 1][k], area[i + 1][k], area[i][k - 1], area[i][k + 1]).minOrNull()!!) {
                basinSizes += explore(i, k).toLong()
            }
        }
    }
    solve(basinSizes.sortedDescending().take(3).reduce(Long::times))


}

