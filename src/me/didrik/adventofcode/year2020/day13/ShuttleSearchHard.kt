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
    do {
        timestamp += step
        if ((timestamp + busses[nextBus].first) % busses[nextBus].second == 0L) {
            step = lcm(step, busses[nextBus++].second)
        }
    } while (nextBus < busses.size)
    println(timestamp)


}

// Dividing by gcd not required since all input are prime
private fun lcm(a: Long, b: Long) =
        a * b / BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).toLong()