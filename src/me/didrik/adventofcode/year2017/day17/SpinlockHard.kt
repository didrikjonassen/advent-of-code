package me.didrik.adventofcode.year2017.day17

fun main(args: Array<String>) {
    val input = 328
    var currentPosition = 0
    var positionOf0 = 0
    var valueAfter0 = 0
    (1 .. 50_000_000)
            .forEach { i ->
                currentPosition += input
                currentPosition %= i
                if (currentPosition == positionOf0) {
                    valueAfter0 = i
                } else if (currentPosition < positionOf0) {
                    positionOf0++
                }
                ++currentPosition
            }
    println("valueAfter0 = $valueAfter0")
}