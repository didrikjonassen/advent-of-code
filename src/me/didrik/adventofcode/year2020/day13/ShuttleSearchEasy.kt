package me.didrik.adventofcode.year2020.day13

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day13/ShuttleSearchInput.txt")
    val input = file.readLines()
    val arriving = input[0].toInt()
    val busses = input[1].split(",").filter { it != "x" }.map { it.toInt() }
    val firstBus = busses.minBy { it - (arriving % it) }!!
    println(firstBus * (firstBus - arriving % firstBus))
}