package me.didrik.adventofcode.year2020.day12

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day12/RainRiskInput.txt")
    val input = file.readLines()

    val pos = IntArray(2)
    val dir = arrayOf(intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(0, -1), intArrayOf(1, 0))
    var chosenDir = 0
    for (line in input) {
        val char = line[0]
        val magnitude = line.substring(1).toInt()
        when (char) {
            'N' -> pos[0] += magnitude
            'S' -> pos[0] -= magnitude
            'E' -> pos[1] += magnitude
            'W' -> pos[1] -= magnitude
            'R' -> chosenDir = (chosenDir + magnitude / 90) % 4
            'L' -> chosenDir = (chosenDir + 4 - magnitude / 90) % 4
            'F' -> {
                pos[0] += dir[chosenDir][0] * magnitude; pos[1] += dir[chosenDir][1] * magnitude
            }
        }
    }
    println(pos.map { it.absoluteValue }.sum())
}
