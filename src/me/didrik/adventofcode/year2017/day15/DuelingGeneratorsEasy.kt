package me.didrik.adventofcode.year2017.day15

fun main(args: Array<String>) {

    val matches = generateSequence(Pair(699L * 16807, 124L * 48271)) {
        Pair(it.first * 16807 % 2147483647, it.second * 48271 % 2147483647) }
            .take(40_000_000)
            .filter { (first, second) ->
                (first xor second) and ((1 shl 16) - 1) == 0L
            }
            .count()

    println("matches = $matches")
}