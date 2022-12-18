package me.didrik.adventofcode.year2022.day18

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day18/Input.txt")
    val input = file.readLines()

    val scan = Array(22) {Array(22) {BooleanArray(22)} }
    input.map { it.split(",").map { it.toInt() + 1 } }
        .forEach { (first, second, third) -> scan[first][second][third] = true }
    val edges = Array(22) { Array(22) { Array<Int?>(22) { null } } }
    bfs(edges, scan, Triple(0, 0, 0))
    val result = edges.sumOf { it.sumOf { it.sumOf { it ?: 0 } } }
    solve(result)
}

private operator fun Triple<Int, Int, Int>.plus(other: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    return Triple(first + other.first, second + other.second, third + other.third)
}

val neighbours = (-1..1).flatMap { i ->
    (-1 .. 1).flatMap { j ->
        (-1 .. 1).map { k ->
            Triple(i, j, k)
        }
    }
}.filter { listOf(it.first, it.second, it.third).count { it == 0 } == 2 }
val grid = 0 .. 21

private fun bfs(edges: Array<Array<Array<Int?>>>, scan: Array<Array<BooleanArray>>, start: Triple<Int, Int, Int>) {
    val queue = ArrayDeque(listOf(start))
    while (queue.isNotEmpty()) {
        val pos = queue.removeFirst()
        if (!pos.insideBounds()) {
            continue
        }
        if (edges[pos.first][pos.second][pos.third] != null) {
            continue
        }
        if (scan[pos.first][pos.second][pos.third]) {
            continue
        }
        edges[pos.first][pos.second][pos.third] = neighbours.map { it + pos }
            .filter { it.insideBounds() }
            .count { scan[it.first][it.second][it.third] }
        queue += neighbours.map { it + pos }
    }
}

private fun Triple<Int, Int, Int>.insideBounds(): Boolean {
    return listOf(first, second, third).all { it in grid }
}