package me.didrik.adventofcode.year2020.day08

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day08/HandheldHaltingInput.txt")
    val input = file.readLines()


    outer@
    for (i in input.indices) {
        val instructions = file.readLines().toMutableList()
        when (instructions[i].split(" ")[0]) {
            "acc" -> continue@outer
            "jmp" -> instructions[i] = instructions[i].replace("jmp", "nop")
            "nop" -> instructions[i] = instructions[i].replace("nop", "jmp")
        }
        var acc = 0
        var pos = 0
        val visited = BooleanArray(input.size)

        while (true) {
            if (pos == input.size) {
                println(acc)
                break@outer
            } else if (pos > input.size || visited[pos]) {
                continue@outer
            } else {
                visited[pos] = true
                val (operation, value) = instructions[pos].split(" ")
                when (operation) {
                    "acc" -> { acc += value.toInt(); pos++ }
                    "jmp" -> pos += value.toInt()
                    "nop" -> pos++
                }
            }
        }

    }
}
