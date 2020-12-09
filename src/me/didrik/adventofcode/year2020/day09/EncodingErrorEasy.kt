package me.didrik.adventofcode.year2020.day09

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day09/EncodingErrorInput.txt")
    val input = file.readLines().map { it.toLong() }
    var preamble = input.slice(0 until 25)
    outer@
    for (number in input.subList(25, input.size)) {
        for (i in preamble.indices) {
            for (k in i + 1 until preamble.size) {
                if (preamble[i] + preamble[k] == number) {
                    preamble = preamble.drop(1) + number
                    continue@outer
                }
            }
        }
        println(number)
        break
    }
}
