package me.didrik.adventofcode.year2017.day10

import java.io.File

fun main(args: Array<String>) {
    val lengths = File("src/me/didrik/adventofcode/year2017/day10/KnotHashInput.txt").readText().toCharArray().map { it.toInt() }.toMutableList()
    val denseHash = knotHash(lengths)

    println("denseHash = $denseHash")
}

fun knotHash(lengths: MutableList<Int>): String {
    lengths.addAll(listOf(17, 31, 73, 47, 23))
    val ropeLength = 256
    val rope = Rope(ropeLength)
    var currentPosition = 0
    var skipSize = 0
    (1..64).forEach {
        lengths.forEach { length ->
            val ropeValuesReversed = (currentPosition until currentPosition + length)
                    .map { rope[it] }
                    .asReversed()
            ropeValuesReversed.forEachIndexed { index, value ->
                rope[currentPosition + index] = value
            }
            currentPosition += length + skipSize++
            currentPosition %= ropeLength
        }
    }

    return rope
            .chunked(16)
            .map { chunk ->
                chunk.reduce(Int::xor)
            }
            .map { it.toString(16) }
            .joinToString(separator = "") { if (it.length == 1) '0' + it else it }
}