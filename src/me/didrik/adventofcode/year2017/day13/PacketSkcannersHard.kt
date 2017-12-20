package me.didrik.adventofcode.year2017.day13

import java.io.File

fun main(args: Array<String>) {
    val layout = File("src/me/didrik/adventofcode/year2017/day13/PacketScannersInput.txt").readLines()
            .map { it.split(": ") }
            .map {
                Pair(Integer.parseInt(it[0]), Integer.parseInt(it[1]))
            }

    var delay = 0
    while (true) {
        val firewallHits = layout
                .filter { (depth, range) ->
                    (delay + depth) % (2 * range - 2) == 0
                }
                .count()
        if (firewallHits == 0) break
        ++delay
    }
    println("delay = $delay")

}