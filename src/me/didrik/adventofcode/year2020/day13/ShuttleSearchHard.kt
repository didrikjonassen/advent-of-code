package me.didrik.adventofcode.year2020.day13

import java.io.File
import java.math.BigInteger

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day13/ShuttleSearchInput.txt")
    val input = file.readLines()
    val busses = input[1].split(",")
            .mapIndexed { index, id -> Pair(index, id) }
            .filterNot { it.second == "x" }
            .map { Pair(it.first.toLong(), it.second.toLong()) }

    var step = busses[0].second
    var nextBus = 1
    var timestamp = 0L
    while (nextBus < busses.size) {
        if ((timestamp + busses[nextBus].first) % busses[nextBus].second == 0L) {
            println(timestamp)
            step = lcm(step, busses[nextBus++].second)
        } else {
            timestamp += step
        }
    }
    println(timestamp)


}
private fun lcm(a: Long, b: Long): Long {
    return a * b / BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).toLong()
}