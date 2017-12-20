package me.didrik.adventofcode.year2017.day15

fun main(args: Array<String>) {

    val generatorA = generateSequence(699L * 16807) {
        it * 16807 % 2147483647
    }
    val generatorB = generateSequence(124L * 48271) {
        it * 48271 % 2147483647
    }

    val matches = generatorA
            .filter { it and 4 - 1 == 0L }
            .zip(generatorB
                    .filter { it and 8 - 1 == 0L }
            )
            .take(5_000_000)
            .filter { (first, second) ->
                (first xor second) and ((1 shl 16) - 1) == 0L
            }
            .count()

    println("matches = $matches")
}