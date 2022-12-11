package me.didrik.adventofcode.year2022.day11

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day11/Input.txt")
    val input = file.readLines()
    val monkeys = mutableListOf<Monkey>()
    input.windowed(6, 7)
        .forEach { lines ->
            val operation = when {
                lines[2].contains("old * old") -> {old: Int -> old * old}
                lines[2].contains("*") -> {old: Int -> old * lines[2].substringAfter("* ").toInt()}
                lines[2].contains("+") -> {old: Int -> old + lines[2].substringAfter("+ ").toInt()}
                else -> throw IllegalStateException()
            }
            monkeys += Monkey(
                lines[0].split(" ").last().substringBefore(":").toInt(),
                lines[1].substringAfter(": ").split(", ").map { it.toInt() }.toMutableList(),
                operation,
                lines[3].substringAfter("by ").toInt(),
                lines[4].substringAfter("monkey ").toInt(),
                lines[5].substringAfter("monkey ").toInt(),
                monkeys
            )
        }
    repeat(20) {
        monkeys.forEach { it.doBusiness() }
    }
    val result = monkeys.sortedByDescending { it.activity }
        .take(2)
        .map { it.activity }
        .reduce(Math::multiplyExact)
    solve(result)
}

private data class Monkey(
    val name: Int,
    val items: MutableList<Int>,
    val operation: (Int) -> Int,
    val test: Int,
    val ifTrue: Int,
    val ifFalse: Int,
    val family: List<Monkey>,
    var activity: Long = 0
) {
    fun doBusiness() {
        activity += items.size
        for (item in items) {
            val newItem = operation(item) / 3
            if (newItem % test == 0) {
                family[ifTrue].items += newItem
            } else {
                family[ifFalse].items += newItem
            }
        }
        items.clear()
    }
}
