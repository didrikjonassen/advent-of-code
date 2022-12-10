package me.didrik.adventofcode.year2022.day10

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day10/Input.txt")
    val input = file.readLines()
    val cycle = mutableListOf(1)
    var x = 1
    input.forEach {
        if (it == "noop") {
            cycle += x
        } else {
            cycle += x
            x += it.substringAfter("addx ").toInt()
            cycle += x
        }
    }
    val sprites = cycle.map { it - 1 .. it + 1 }
    val image = Array(6) { CharArray(40) }
    for (i in 0 until 6) {
        for (k in 0 until 40) {
            image[i][k] = if (k in sprites[40 * i + k]) {
                '#'
            } else {
                '.'
            }
        }
    }
    image.forEach { println(it.joinToString("")) }
}