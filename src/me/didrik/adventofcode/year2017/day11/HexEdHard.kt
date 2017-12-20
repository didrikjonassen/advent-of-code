package me.didrik.adventofcode.year2017.day11

import java.io.File

fun main(args: Array<String>) {
    val path = File("src/me/didrik/adventofcode/year2017/day11/HexEdInput.txt").readText().split(',')
    val grid = Grid()
    val furthestAway =
            path.map { direction ->
                grid += direction
                grid.distance()
            }.max()
    println("furthestAway = $furthestAway")
}