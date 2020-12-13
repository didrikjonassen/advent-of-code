package me.didrik.adventofcode.year2020.day13

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day13/ShuttleSearchInput.txt")
    val input = file.readLines()
    val arriving = input[0].toInt()
    val busses = input[1].split(",").filterNot { it == "x" }.map { it.toInt() }
    // arriving % bussId is how many minutes before arrival it last was at the stop.
    val firstBus = busses.minBy { bussId -> bussId - (arriving % bussId) }!!
    println(firstBus * (firstBus - arriving % firstBus))
}