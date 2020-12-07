package me.didrik.adventofcode.year2020.day07

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day07/HandyHaversacksInput.txt")
    val input = file.readLines()
            .map { it.removeSuffix(".") }
            .map { it.replace("bags", "bag") }
    val map = mutableMapOf<String, List<Pair<Long, String>>>()
    for (line in input) {
        val (outer, inners) = line.split(" contain ")
        if (inners == "no other bag") continue
        map[outer] = inners.split(", ")
                .map { Pair(it[0].toString().toLong(), it.substring(2)) }
    }
    println(map.traverse("shiny gold bag") - 1)
}

private fun MutableMap<String, List<Pair<Long, String>>>.traverse(root: String): Long {
    return (get(root)
            ?.map { it.first * traverse(it.second) }
            ?.sum()
            ?: 0L) + 1L
}