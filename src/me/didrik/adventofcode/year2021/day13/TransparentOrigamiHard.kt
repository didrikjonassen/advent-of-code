package me.didrik.adventofcode.year2021.day13

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day13/Input.txt")
    val input = file.readLines()

    var dots = input.takeWhile { it.isNotEmpty() }.map { it.split(",").map { it.toInt() } }.map { it[0] to it[1] }.toSet()
    val instructions = input.dropWhile { it.isNotEmpty() }.drop(1)

    for (instruction in instructions) {
        val (direction, lineString) = instruction.split("=")
        val line = lineString.toInt()
        dots = when (direction) {
            "fold along x" -> dots.map { (x, y) ->
                (if (x > line) line - (x - line) else x) to y
            }.toSet()
            "fold along y" -> dots.map { (x, y) ->
                x to (if (y > line) line - (y - line) else y)
            }.toSet()
            else -> throw IllegalStateException()
        }
    }
    val maxX = dots.maxOf { it.first }
    val maxY = dots.maxOf { it.second }
    val grid = Array(maxY + 1) { BooleanArray(maxX + 1) }
    dots.forEach { (x, y) ->
        grid[y][x] = true
    }
    grid.forEach {
        println(it.toList().joinToString("") { if (it) "#" else " " })
    }
}