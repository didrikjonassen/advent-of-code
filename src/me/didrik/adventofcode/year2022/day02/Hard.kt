package me.didrik.adventofcode.year2022.day02

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day02/Input.txt")
    val input = file.readLines()
    val sum = input.sumOf {
        val (elf, player) = it.split(" ")
        points(elf, player)
    }
    solve(sum)
}

private fun points(elf: String, player: String): Int {
    return when {
        elf == "A" && player == "X" -> 0 + 3
        elf == "A" && player == "Y" -> 3 + 1
        elf == "A" && player == "Z" -> 6 + 2
        elf == "B" && player == "X" -> 0 + 1
        elf == "B" && player == "Y" -> 3 + 2
        elf == "B" && player == "Z" -> 6 + 3
        elf == "C" && player == "X" -> 0 + 2
        elf == "C" && player == "Y" -> 3 + 3
        elf == "C" && player == "Z" -> 6 + 1
        else -> throw IllegalStateException()
    }
}