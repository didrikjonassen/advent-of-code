package me.didrik.adventofcode.year2020.day07

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day07/HandyHaversacksInput.txt")
    val input = file.readLines()
            .map { it.removeSuffix(".") }
            .map { it.replace("bags", "bag") }
    val map = mutableMapOf<String, List<String>>()
    for (line in input) {
        val (outer, inners) = line.split(" contain ")
        for (inner in inners.split(", ")) {
            if (inner != "no other bag") {
                val singleInner = inner.substring(2)
                map[singleInner] = map.getOrDefault(singleInner, listOf()) + outer
            }
        }
    }

    val possibleBags = mutableSetOf<String>()
    map.traverse("shiny gold bag", possibleBags)
    println("possibleBags.size = ${possibleBags.size}")
}

private fun MutableMap<String, List<String>>.traverse(current: String, acc: MutableSet<String>) {
    for (bag in get(current) ?: return) {
        acc += bag
        traverse(bag, acc)
    }
}