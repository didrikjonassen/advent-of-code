package me.didrik.adventofcode.year2020.day08

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day08/HandheldHaltingInput.txt")
    val input = file.readLines()
    var acc = 0
    var pos = 0
    val visited = BooleanArray(input.size)

    while (true) {
        val (a, b) = input[pos].split(" ")
        when (a) {
            "acc" -> { acc += b.toInt(); pos++; }
            "jmp" -> pos += b.toInt()
            "nop" -> pos++
        }
        if (visited[pos]) {
            println(acc)
            break
        } else {
            visited[pos] = true
        }
    }
}