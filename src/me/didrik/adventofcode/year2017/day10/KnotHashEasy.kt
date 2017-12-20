package me.didrik.adventofcode.year2017.day10

import java.io.File

fun main(args: Array<String>) {
    val lengths = File("src/me/didrik/adventofcode/year2017/day10/KnotHashInput.txt").readText().split(',').map { Integer.parseInt(it) }
    val ropeSize = 256
    val rope = Rope(ropeSize)
    var currentPosition = 0
    var skipSize = 0
    lengths.forEach {
        length ->
        val ropeValuesReversed = (currentPosition until currentPosition + length)
                .map { rope[it] }
                .asReversed()
        ropeValuesReversed.forEachIndexed {
            index, value ->
            rope[currentPosition + index] = value
        }
        currentPosition += length + skipSize++
        currentPosition %= ropeSize
    }

    println("rope[0] * rope[1] = ${rope[0] * rope[1]}")
}