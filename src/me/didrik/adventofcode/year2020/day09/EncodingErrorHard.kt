package me.didrik.adventofcode.year2020.day09

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day09/EncodingErrorInput.txt")
    val input = file.readLines().map { it.toLong() }
    val answer = 373803594L
    var min = 0
    var max = 2
    while (true) {
        val sum = input.subList(min, max).sum()
        if (sum == answer) {
            println(input.subList(min, max).min()!! + input.subList(min, max).max()!!)
            break
        } else if (sum < answer) {
            max++
        } else if (sum > answer) {
            min++
        }
    }
}
