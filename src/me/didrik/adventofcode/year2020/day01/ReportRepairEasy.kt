package me.didrik.adventofcode.year2020.day01

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day01/ReportRepairInput.txt")
    val array = BooleanArray(2021)
    file.readLines()
            .map { it.toInt() }
            .filter { it <= 2020 }
            .forEach { array[it] = true }

    for (i in 1..1010) {
        if (array[i] && array[2020 - i]) {
            println("answer = ${i * (2020 - i)}")
            break
        }
    }

}