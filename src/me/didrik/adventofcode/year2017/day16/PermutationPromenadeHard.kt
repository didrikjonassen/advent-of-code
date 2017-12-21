package me.didrik.adventofcode.year2017.day16

import java.io.File

fun main(args: Array<String>) {
    val moves = File("src/me/didrik/adventofcode/year2017/day16/PermutationPromenadeInput.txt").readText().split(',')
    val line = Line()

    val seenPermutations = ArrayList<String>()

    while (true) {
        moves.forEach { move ->
            line.move(move)
        }
        val permutation = line.programs.joinToString(separator = "")
        if (permutation in seenPermutations) {
            val iterations = seenPermutations.size + 1
            val cycleStart = seenPermutations.indexOf(permutation)
            val cycleLength = seenPermutations.size - cycleStart

            val finalPosition = seenPermutations[cycleStart + (1_000_000_000 - iterations) % cycleLength]
            println("finalPosition = $finalPosition")
            break
        }
        seenPermutations.add(permutation)
    }

}

