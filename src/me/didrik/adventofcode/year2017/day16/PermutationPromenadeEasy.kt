package me.didrik.adventofcode.year2017.day16

import java.io.File

fun main(args: Array<String>) {
    val moves = File("src/me/didrik/adventofcode/year2017/day16/PermutationPromenadeInput.txt").readText().split(',')
    val line = Line()
    (0 until 2).forEach {
        moves.forEach { move ->
            line.move(move)
        }
    }

    val finalPosition = line.programs.joinToString(separator = "")
    println("finalPosition = $finalPosition")

}