package me.didrik.adventofcode.year2020.day03

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day03/TobogganTrajectoryInput.txt")
    val input = file.readLines()
    val width = input[0].length

    var treesMultiplied = 1L
    for ((down, right) in listOf(Pair(1, 1), Pair(1, 3), Pair(1, 5), Pair(1, 7), Pair(2, 1))) {
        var trees = 0L
        for (i in input.indices step down) {
            if (input[i][(i * right / down) % width] == '#') {
                trees++
            }
        }
        treesMultiplied *= trees
    }
    println("blah = $treesMultiplied")
}