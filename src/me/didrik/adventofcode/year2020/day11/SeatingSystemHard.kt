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
            grid[1][line][char] = input[line - 1][char - 1]
        }
    }

    val directions = (-1..1).flatMap { i -> (-1..1).map { k -> Pair(i, k) } }
            .filterNot { it.first == 0 && it.second == 0 }

    var gen = 0
    do {
        gen = gen xor 1
        for (i in 1..y) {
            for (k in 1..x) {
                if (grid[gen][i][k] == '.') continue
                val occupied = directions
                        .map { dir -> grid[gen xor 1].search(i, k, dir) }
                        .filter { it == '#' }
                        .count()
                when (occupied) {
                    0 -> grid[gen][i][k] = '#'
                    in 5..8 -> grid[gen][i][k] = 'L'
                    else -> grid[gen][i][k] = grid[gen xor 1][i][k]
                }
            }
        }
    } while (!grid[0].contentDeepEquals(grid[1]))
    val endOccupied = grid[gen].flatMap { it.asList() }
            .filter { it == '#' }
            .count()

    println("endOccupied = $endOccupied")


}

private tailrec fun Array<CharArray>.search(y: Int, x: Int, dir: Pair<Int, Int>): Char {
    return if (this[y + dir.first][x + dir.second] == '.') {
        this.search(y + dir.first, x + dir.second, dir)
    } else {
        this[y + dir.first][x + dir.second]
    }
}