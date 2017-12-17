package me.didrik.adventofcode.year2017.day6

import java.io.File

fun main(args: Array<String>) {
    val memory = File("src/me/didrik/adventofcode/year2017/day6/MemoryReallocationInput.txt").readText().split('\t').map { Integer.parseInt(it) }.toIntArray()
    val encounteredRedistributions = HashMap<String, Int>()
    do {
        encounteredRedistributions.put(memory.joinToString(), encounteredRedistributions.size)
        val max = memory.max()!!
        val maxPos = memory.indexOf(max)
        memory[maxPos] = 0
        val quotient = max/memory.size
        val remainder = max % memory.size
        (0 until memory.size)
                .forEach {
                    i ->
                    memory[i] += quotient
                }
        (1 .. remainder)
                .forEach {
                    i ->
                    memory[(maxPos + i) % memory.size]++
                }
    } while (!encounteredRedistributions.contains(memory.joinToString()))
    println(encounteredRedistributions.size - encounteredRedistributions[memory.joinToString()]!!)
}