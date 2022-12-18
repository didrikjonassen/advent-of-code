package me.didrik.adventofcode.year2022.day17

import me.didrik.adventofcode.solve
import java.io.File

private val down = 0 to -1
private val left = -1 to 0
private val right = 1 to 0

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day17/Input.txt")
    val jetPattern = file.readLines().first()
    val shapes = listOf(
        setOf(2 to 0, 3 to 0, 4 to 0, 5 to 0),
        setOf(3 to 0, 2 to 1, 3 to 1, 4 to 1, 3 to 2),
        setOf(2 to 0, 3 to 0, 4 to 0, 4 to 1, 4 to 2),
        setOf(2 to 0, 2 to 1, 2 to 2, 2 to 3),
        setOf(2 to 0, 3 to 0, 2 to 1, 3 to 1)
    )
    var maxHeight = 0
    val boundary = 0 until 7
    var stones = 0
    var patternPos = 0
    val filled = HashSet(boundary.map { it to 0 })
    while (stones < 2022) {
        var stone = shapes[(stones++ % shapes.size)].map { it.first to it.second + maxHeight + 4 }
        while (true) {
            val push = if(jetPattern[patternPos] == '<') left else right
            patternPos = (patternPos + 1) % jetPattern.length
            val pushed = stone.map { it + push }
            if (pushed.all { it.first in boundary } && pushed.intersect(filled).isEmpty())
                stone = pushed
            val gravitied = stone.map { it + down }
            if (gravitied.intersect(filled).isEmpty()) {
                stone = gravitied
            } else {
                filled += stone
                if (stone.any { it.second > maxHeight })
                    maxHeight = stone.maxOf { it.second }
                break
            }
        }
    }
    solve(maxHeight)
}

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return first + other.first to second + other.second
}