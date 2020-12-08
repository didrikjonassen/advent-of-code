package me.didrik.adventofcode.year2020.day08

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day08/HandheldHaltingInput.txt")
    val input = file.readLines()


    outer@
    for (i in input.indices) {
        val innerInput = file.readLines().toMutableList()
        when (innerInput[i].split(" ")[0]) {
            "acc" -> continue@outer
            "jmp" -> innerInput[i] = innerInput[i].replace("jmp", "nop")
            "nop" -> innerInput[i] = innerInput[i].replace("nop", "jmp")
        }
        var acc = 0
        var pos = 0
        val visited = BooleanArray(input.size)

        while (true) {
            val (a, b) = innerInput[pos].split(" ")
            when (a) {
                "acc" -> { acc += b.toInt(); pos++ }
                "jmp" -> pos += b.toInt()
                "nop" -> pos++
            }
            if (pos == input.size) {
                println(acc)
                break@outer
            } else if (pos > input.size || visited[pos]) {
                continue@outer
            } else {
                visited[pos] = true
            }
        }

    }
}