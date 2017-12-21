package me.didrik.adventofcode.year2017.day16

import java.io.File

// Doesn't work because of the moves of type "pA/B".
fun main(args: Array<String>) {
    val moves = File("src/me/didrik/adventofcode/year2017/day16/PermutationPromenadeInput.txt").readText().split(',')
    val line = Line()
    moves.forEach { move ->
        line.move(move)
    }
    val finalPosition = line.programs.joinToString(separator = "")

    val patterns = HashMap<Int, String>()
    patterns.put(1, finalPosition)

    var iterations = 1_000_000_000
    var i = 2
    do {
        patterns.put(i, moveWithPattern(patterns[i ushr 1]!!, patterns[i ushr 1]!!))
        i = i shl 1
    } while (i < iterations)

    var positions = "abcdefghijklmnop"
    while (iterations != 0) {
        val leastSignificantBit = iterations and -iterations
        positions = moveWithPattern(positions, patterns[leastSignificantBit]!!)
        iterations = iterations xor leastSignificantBit
    }

    println("positions = $positions")
}

fun moveWithPattern(input: String, pattern: String): String {
    return pattern
            .map { char ->
                input[char - 'a']
            }.joinToString(separator = "")
}