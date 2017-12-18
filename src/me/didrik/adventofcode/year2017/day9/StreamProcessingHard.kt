package me.didrik.adventofcode.year2017.day9

import java.io.File

fun main(args: Array<String>) {
    val stream = File("src/me/didrik/adventofcode/year2017/day9/StreamProcessingInput.txt").readText().substring(1).toCharArray().iterator()
    val root = Group(stream, 1)
    println("root.cumulativeScore = ${root.cumulativeScore}")
}
