package me.didrik.adventofcode.year2020.day24

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day24/LobbyLayoutInput.txt")
    val input = file.readLines().map { it.tokenize() }
    val initialGrid = mutableSetOf<Triple<Int, Int, Int>>()

    for (line in input) {
        val coordinate = line.map {
            when (it) {
                "e" -> Triple(1, -1, 0)
                "w" -> Triple(-1, 1, 0)
                "ne" -> Triple(1, 0, -1)
                "sw" -> Triple(-1, 0, 1)
                "nw" -> Triple(0, 1, -1)
                "se" -> Triple(0, -1, 1)
                else -> throw IllegalArgumentException("Illegal direction")
            }
        }.reduce { acc, triple -> acc + triple }

        if (coordinate in initialGrid) {
            initialGrid -= coordinate
        } else {
            initialGrid += coordinate
        }
    }

    var grid = initialGrid.toSet()
    repeat(100) {
        val nextGrid = grid.flatMap { hexNeighbours().map { neighbour -> neighbour + it } }
            .groupBy { it }
            .filter { it.value.size == 2 || (it.key in grid && it.value.size == 1) }
            .map { it.key }

        grid = nextGrid.toSet()
    }

    solve(grid.size)
}

private fun String.tokenize(): List<String> {
    var i = 0
    val tokens = mutableListOf<String>()
    while (i < length) {
        tokens += when (get(i)) {
            'e', 'w' -> {
                get(i++).toString()
            }
            else -> {
                i += 2
                substring(i - 2, i)
            }
        }
    }
    return tokens
}

private operator fun Triple<Int, Int, Int>.plus(other: Triple<Int, Int, Int>) =
    Triple(
        first + other.first,
        second + other.second,
        third + other.third
    )

private fun hexNeighbours() =
    listOf(
        Triple(1, -1, 0),
        Triple(-1, 1, 0),
        Triple(1, 0, -1),
        Triple(-1, 0, 1),
        Triple(0, 1, -1),
        Triple(0, -1, 1),
    )