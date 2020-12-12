package me.didrik.adventofcode.year2020.day12

import java.io.File
import kotlin.math.absoluteValue

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day12/RainRiskInput.txt")
    val input = file.readLines()

    val pos = IntArray(2)
    val waypoint = intArrayOf(1, 10)
    for (line in input) {
        val char = line[0]
        val magnitude = line.substring(1).toInt()
        when (char) {
            'N' -> waypoint[0] += magnitude
            'S' -> waypoint[0] -= magnitude
            'E' -> waypoint[1] += magnitude
            'W' -> waypoint[1] -= magnitude
            'R' -> repeat(magnitude / 90) {waypoint.turnRight()}
            'L' -> repeat(magnitude / 90) {waypoint.turnLeft()}
            'F' -> {
                pos[0] += waypoint[0] * magnitude; pos[1] += waypoint[1] * magnitude
            }
        }
    }
    println(pos.map { it.absoluteValue }.sum())
}

private fun IntArray.turnRight() {
    val ns = this[0]
    val ew = this[1]
    this[0] = -ew
    this[1] = ns
}

private fun IntArray.turnLeft() {
    val ns = this[0]
    val ew = this[1]
    this[0] = ew
    this[1] = -ns
}
