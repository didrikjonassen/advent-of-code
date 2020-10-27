package me.didrik.adventofcode.year2018.day03

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2018/day03/NoMatterHowYouSliceItInput.txt")
    val input = file.readLines()

    val fabric = Array(1000) { IntArray(1000) }
    val format = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

    for (line in input) {
        val (_, leftPad, topPad, leftSize, topSize) = format.matchEntire(line)!!.destructured.toList().map { it.toInt() }
        for (i in leftPad until leftPad + leftSize) {
            for (k in topPad until topPad + topSize) {
                fabric[i][k]++
            }
        }
    }

    val overlapped = fabric
            .flatMap { it.toList() }
            .filter { it > 1 }
            .count()
    println("overlapped = $overlapped")

}