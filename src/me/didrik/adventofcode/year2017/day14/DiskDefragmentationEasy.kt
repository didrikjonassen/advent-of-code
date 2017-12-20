package me.didrik.adventofcode.year2017.day14

import me.didrik.adventofcode.year2017.day10.knotHash
import java.io.File

fun main(args: Array<String>) {
    val lengths = File("src/me/didrik/adventofcode/year2017/day14/DiskDefragmentationInput.txt").readText().toCharArray().map { it.toInt() }
    val usedSquares = (0 until 128)
            .joinToString(separator = "") { i ->
                knotHash((lengths + "-$i".toCharArray().map { it.toInt() }).toMutableList())
            }
            .map { Integer.parseInt(it.toString(), 16) }
            .joinToString(separator = "") { it.toString(2) }
            .filter { it == '1' }
            .count()
    println("usedSquares = $usedSquares")
}