package me.didrik.adventofcode.year2020.day14

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day14/DockingDataInput.txt")
    val input = file.readLines()
    var andMask = 0L
    var orMask = 0L
    val memory = mutableMapOf<Long, Long>()
    for (line in input) {
        if (line.startsWith("mask")) {
            val mask = line.split(" = ")[1]
            andMask = mask.replace("X", "1").toLong(2)
            orMask = mask.replace("X", "0").toLong(2)
        } else {
            val (address, value) = """mem\[(\d+)] = (\d+)""".toRegex().matchEntire(line)!!.destructured.toList().map { it.toLong() }
            memory[address] = value and andMask or orMask
        }
    }
    println(memory.values.sum())
}