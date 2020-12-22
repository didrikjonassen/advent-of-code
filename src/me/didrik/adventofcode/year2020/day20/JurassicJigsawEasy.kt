package me.didrik.adventofcode.year2020.day20

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day20/JurassicJigsawInput.txt")
    val input = file.readLines()

    val tilePositions = input
            .mapIndexed { index, row ->
                Pair(index, row)
            }.filter { it.second.startsWith("Tile") }
            .map { it.first }

    val tiles = tilePositions
            .map {
                Pair(
                        input[it].substringAfter(" ").substringBefore(":").toLong(),
                        input.subList(it + 1, it + 11)
                )
            }

    val map = mutableMapOf<String, Long>()

    for (tile in tiles) {
        tile.putAllEdgesIn(map)
    }

    val answer = map.values
            .asSequence()
            .filter{ it < 10_000 }
            .groupBy { it }
            .filter { it.value.size == 2 }
            .map { it.key }
            .reduce(Long::times)
    solve(answer)

}

private fun Pair<Long, List<String>>.putAllEdgesIn(map: MutableMap<String, Long>) {
    val top = second[0].toCanonicalEdge()
    val bottom = second[9].toCanonicalEdge()
    val left = second.map { it[0] }.joinToString(separator = "").toCanonicalEdge()
    val right = second.map { it[9] }.joinToString(separator = "").toCanonicalEdge()

    map[top] = first * (map[top] ?: 1L)
    map[bottom] = first * (map[bottom] ?: 1L)
    map[left] = first * (map[left] ?: 1L)
    map[right] = first * (map[right] ?: 1L)
}

private fun String.toCanonicalEdge(): String {
    return listOf(this, this.reversed()).sorted().joinToString(separator = "")
}