package me.didrik.adventofcode.year2020.day14

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day14/DockingDataInput.txt")
    val input = file.readLines()
    var mask = ""
    var orMask = 0L
    val memory = mutableMapOf<Long, Long>()
    for (line in input) {
        if (line.startsWith("mask")) {
            mask = line.split(" = ")[1]
            orMask = mask.replace("X", "0").toLong(2)
        } else {
            val (address, value) = """mem\[(\d+)] = (\d+)""".toRegex().matchEntire(line)!!.destructured.toList().map { it.toLong() }
            memory.recursiveSet(mask, address or orMask, value)
        }
    }
    println(memory.values.sum())



}

private fun MutableMap<Long, Long>.recursiveSet(mask: String, address: Long, value: Long) {
    if ("X" !in mask) {
        return
    } else {
        val bitToFlip = 1L shl (35 - mask.indexOf("X"))
        set(address, value)
        recursiveSet(mask.replaceFirst('X', 'Y'), address, value)
        set(address xor bitToFlip, value)
        recursiveSet(mask.replaceFirst('X', 'Y'), address xor bitToFlip, value)
    }
}