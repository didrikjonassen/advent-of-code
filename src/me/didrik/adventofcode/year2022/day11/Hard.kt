package me.didrik.adventofcode.year2022.day11

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day11/Input.txt")
    val input = file.readLines()
    val monkeys = mutableListOf<Monkey2>()
    input.windowed(6, 7)
        .forEach { lines ->
            val operation = when {
                lines[2].contains("old * old") -> {old: Long -> old * old}
                lines[2].contains("*") -> {old: Long -> old * lines[2].substringAfter("* ").toLong()}
                lines[2].contains("+") -> {old: Long -> old + lines[2].substringAfter("+ ").toLong()}
                else -> throw IllegalStateException()
            }
            monkeys += Monkey2(
                lines[0].split(" ").last().substringBefore(":").toInt(),
                lines[1].substringAfter(": ").split(", ").map { it.toLong() }.toMutableList(),
                operation,
                lines[3].substringAfter("by ").toLong(),
                lines[4].substringAfter("monkey ").toInt(),
                lines[5].substringAfter("monkey ").toInt(),
                monkeys
            )
        }
    repeat(10000) {
        monkeys.forEach { it.doBusiness() }
    }
    val result = monkeys.sortedByDescending { it.activity }
        .take(2)
        .map { it.activity }
        .reduce(Math::multiplyExact)
    solve(result)
}

private data class Monkey2(
    val name: Int,
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val test: Long,
    val ifTrue: Int,
    val ifFalse: Int,
    val family: List<Monkey2>,
    var activity: Long = 0
) {
    fun doBusiness() {
        val commonDivisor = family.map { it.test }.reduce(Math::multiplyExact)
        activity += items.size
        for (item in items) {
            val newItem = operation(item) % commonDivisor
            if (newItem % test == 0L) {
                family[ifTrue].items += newItem
            } else {
                family[ifFalse].items += newItem
            }
        }
        items.clear()
    }
}