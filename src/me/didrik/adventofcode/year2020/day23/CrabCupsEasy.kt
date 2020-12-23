package me.didrik.adventofcode.year2020.day23

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day23/CrabCupsInput.txt")
    val input = file.readLines()[0].toList().map { it.toString().toInt() }
    var cups = input
    var current = input[0]

    repeat(100) {
        val pickUp = (cups + cups).dropWhile { it != current }.drop(1).take(3)
        cups = cups - pickUp
        val dest = cups.filter { it < current }.maxOrNull() ?: cups.maxOrNull()!!
        cups = cups.takeWhile { it != dest } + dest + pickUp + cups.dropWhile { it != dest }.drop(1)
        current = cups[(cups.indexOf(current) + 1) % cups.size]
    }

    solve((cups + cups).dropWhile { it != 1 }.drop(1).take(8).joinToString(separator = ""))
}