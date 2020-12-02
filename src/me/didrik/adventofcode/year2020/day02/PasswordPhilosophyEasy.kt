package me.didrik.adventofcode.year2020.day02

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day02/PasswordPhilosophyInput.txt")
    val input = file.readLines()
    var counter = 0
    for (line in input) {
        val (min, max, char, password) = """(\d+)-(\d+) (.): (\w+)""".toRegex().matchEntire(line)!!.destructured
        val count = password.filter { it.toString() == char }.count()
        if (count >= min.toInt() && count <= max.toInt())
            counter++
    }

    println(counter)
}