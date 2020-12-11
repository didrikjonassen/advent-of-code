package me.didrik.adventofcode.year2020.day11

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day11/SeatingSystemInput.txt")
    val input = file.readLines()
    val y = input.size
    val x = input[0].length

    val grid = Array(2) { Array(y + 2) { CharArray(x + 2) } }
    for (line in 1..y) {
        for (char in 1..x) {
            grid[0][line][char] = input[line - 1][char - 1]
        }
    }

    var generation = 0
    while (!grid[0].contentDeepEquals(grid[1])) {
        generation += (generation + 1) % 2
        for (i in 1..y) {
            for (k in 1..x) {
                if (grid[(generation + 1) % 2][i][k] == '.') {
                    grid[generation][i][k] = '.'
                    continue
                }
                val occupied = (-1..1).flatMap { i2 ->
                    (-1..1).map { k2 ->
                        Pair(i2, k2)
                    }
                }
                        .filterNot { it.first == 0 && it.second == 0 }
                        .map { grid[(generation + 1) % 2].search(i, k, it) }
                        .filter { it == '#' }
                        .count()
                when (occupied) {
                    0 -> grid[generation][i][k] = '#'
                    in 5..8 -> grid[generation][i][k] = 'L'
                    else -> grid[generation][i][k] = grid[(generation + 1) % 2][i][k]
                }
            }
        }
    }
    val endOccupied = grid[generation].flatMap { it.asList() }
            .filter { it == '#' }
            .count()

    println("endOccupied = $endOccupied")


}

private tailrec fun Array<CharArray>.search(y: Int, x: Int, direction: Pair<Int, Int>): Char {
    return if (this[y + direction.first][x + direction.second] == '.') {
        this.search(y + direction.first, x + direction.second, direction)
    } else {
        this[y + direction.first][x + direction.second]
    }
}