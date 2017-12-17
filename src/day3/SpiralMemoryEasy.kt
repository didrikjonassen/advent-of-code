package day3

import java.io.File

fun main(args: Array<String>) {
    val input = Integer.parseInt(File ("src/day3/SpiralMemoryInput.txt").readText())
    var layer = 1
    var bottomRight = 9
    while (bottomRight < input) {
        layer++
        bottomRight += 4*2*layer
    }
    val bottomLeft = bottomRight - 2*layer
    val topLeft = bottomLeft - 2*layer
    val topRight = topLeft - 2*layer
    val distance = when {
        input >= bottomLeft -> distanceFromCenter(bottomLeft, bottomRight, input)
        input >= topLeft -> distanceFromCenter(topLeft, bottomLeft, input)
        input >= topRight -> distanceFromCenter(topRight, topLeft, input)
        else -> distanceFromCenter(topRight - 2*layer, topRight, input)
    }
    println("Distance = ${layer + distance}")

}

fun distanceFromCenter(start: Int, stop: Int, value: Int) =
        Math.abs(value - (start+stop)/2)