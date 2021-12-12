package me.didrik.adventofcode.year2021.day12

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day12/Input.txt")
    val input = file.readLines()

    val edges = mutableMapOf<String, List<String>>()
    for (line in input) {
        val (one, two) = line.split("-")
        if (two != "start") edges[one] = edges[one]?.plus(two) ?: listOf(two)
        if (one != "start") edges[two] = edges[two]?.plus(one) ?: listOf(one)
    }

    var count = 0
    fun search(visited: MutableSet<String>, current: String, doubleUsed: Boolean) {
        if (current == "end") {
            count ++
            return
        }
        if (current in visited) {
            if (doubleUsed) {
                return
            } else {
                for (node in edges[current]!!) {
                    search(visited, node, true)
                }
                return
            }
        }

        if (current.first().isLowerCase()) {
            visited += current
        }
        for (node in edges[current]!!) {
            search(visited, node, doubleUsed)
        }
        visited -= current
    }

    search(mutableSetOf(), "start", false)
    solve(count)
}