package me.didrik.adventofcode.year2020.day03

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day03/TobogganTrajectoryInput.txt")
    val input = file.readLines()
    val trees = input
            .mapIndexed {i, line ->
                line[i * 3 % line.length]
            }.filter { it == '#' }
            .count()

    println("trees = $trees")
}