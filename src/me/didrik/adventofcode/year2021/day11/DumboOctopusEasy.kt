package me.didrik.adventofcode.year2021.day11

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day11/Input.txt")
    val input = file.readLines()
    val n = input.size
    val m = input.first().length
    val energy = Array(n + 2) { IntArray(m + 2) }
    val flashed = Array(n + 2) { BooleanArray(m + 2) { true } }
    for (i in 1..n) {
        for (k in 1..m) {
            energy[i][k] = input[i - 1][k - 1].toString().toInt()
            flashed[i][k] = false
        }
    }

    val neighbours = (-1..1).flatMap { first -> (-1..1).map { second -> Pair(first, second) } }
        .filterNot { it.first == 0 && it.second == 0 }

    fun exciteNeighbours(i: Int, k: Int) {
        for ((id, kd) in neighbours) {
            if (++energy[i + id][k + kd] > 9 && !flashed[i + id][k + kd]) {
                flashed[i + id][k + kd] = true
                exciteNeighbours(i + id, k + kd)
            }
        }
    }

    val days = 100
    var flashes = 0L
    repeat(days) {
        for (i in 1..n) {
            for (k in 1..m) {
                if (++energy[i][k] > 9 && !flashed[i][k]) {
                    flashed[i][k] = true
                    exciteNeighbours(i, k)
                }
            }
        }

        for (i in 1..n) {
            for (k in 1..m) {
                if (flashed[i][k]) {
                    flashed[i][k] = false
                    flashes++
                    energy[i][k] = 0
                }
            }
        }
    }

    solve(flashes)
}