package me.didrik.adventofcode.year2020.day01

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day01/ReportRepairInput.txt")
    val array = BooleanArray(2021)
    file.readLines()
            .map { it.toInt() }
            .filter { it <= 2020 }
            .forEach { array[it] = true }

    val entries = array
            .mapIndexed { index, b -> Pair(index, b) }
            .filter { it.second }
            .map { it.first }

    outer@
    for (entry in entries) {
        for (i in 1..(2020 - entry)) {
            if (array[i] && array[2020 - entry - i]) {
                println("answer = ${i * (2020 - entry - i) * entry}")
                break@outer
            }
        }
    }
}