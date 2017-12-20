package me.didrik.adventofcode.year2017.day13

import java.io.File

fun main(args: Array<String>) {
    val layout = File("src/me/didrik/adventofcode/year2017/day13/PacketScannersInput.txt").readLines()
            .map { it.split(": ") }
            .map {
                Pair(Integer.parseInt(it[0]), Integer.parseInt(it[1]))
            }
    val severity = layout.filter { (depth, range) ->
        depth % (2 * range - 2) == 0
    }
            .map { (depth, range) -> depth * range }
            .sum()
    println("severity = $severity")

}