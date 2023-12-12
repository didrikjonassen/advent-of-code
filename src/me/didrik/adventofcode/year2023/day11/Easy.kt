package me.didrik.adventofcode.year2023.day11

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.abs

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day11/Input.txt")
    val image = file.readLines().map { it.toCharArray() }
    val expandedRows = image.flatMap {
        if (it.all { it == '.' }) {
            listOf(it, it)
        } else {
            listOf(it)
        }
    }
    val expandedColumns = Array(expandedRows.size) { mutableListOf<Char>() }
    for (col in expandedRows[0].indices) {
        val column = expandedRows.indices.map { expandedRows[it][col] }
        for (k in column.indices) {
            expandedColumns[k] += column[k]
        }
        if (column.all { it == '.' }) {
            for (k in column.indices) {
                expandedColumns[k] += column[k]
            }
        }
    }
    val galaxies = mutableListOf<Pair<Int, Int>>()
    var sum = 0L
    expandedColumns.forEachIndexed {i, line ->
        line.forEachIndexed { k, c ->
            if (c == '#') {
                val galaxy = i to k
                sum += galaxies.sumOf { distance(galaxy, it) }
                galaxies += galaxy
            }
        }
    }
    solve(sum)
}

private fun distance(a: Pair<Int, Int>, b: Pair<Int, Int>): Int {
    return abs(a.first - b.first) + abs(a.second - b.second)
}