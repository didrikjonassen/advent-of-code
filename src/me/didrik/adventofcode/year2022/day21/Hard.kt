package me.didrik.adventofcode.year2022.day21

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day21/Input.txt")
    val monkeys = file.readLines()
        .map { it.split(": ") }
        .associate { it[0] to it[1] }
    var root = monkeys["root"]!!.split(" + ", " - ", " / ", " * ")
    if (eval(root[0], monkeys, 0) == eval(root[0], monkeys, Long.MAX_VALUE)) {
        root = root.reversed()
    }

    // humn is in left path for my input
    val result = binarySearch(root, monkeys) { i, j -> i < j } ?: binarySearch(root, monkeys) { i, j -> i > j }
    solve(result)
}

private fun binarySearch(root: List<String>, monkeys: Map<String, String>, compare: (Double, Double) -> Boolean): Long? {
    val goal = eval(root[1], monkeys, 0)
    var min = 0L
    var max = Long.MAX_VALUE
    var mid = Long.MAX_VALUE / 2
    while (true) {
        val result = eval(root[0], monkeys, mid)
        val oldMid = mid
        if (result == goal) {
            break
        } else if (compare(result, goal)) { // < doesn't find a result, so it must be >
            min = mid
            mid = (min / 2 + max / 2)
        } else {
            max = mid
            mid = (min / 2 + max / 2)
        }
        if (oldMid == mid) {
            return null
        }
    }
    return mid
}

private fun eval(monkey: String, monkeys: Map<String, String>, humn: Long): Double {
    if (monkey == "humn") {
        return humn.toDouble()
    }
    val number = monkeys[monkey]?.toDoubleOrNull()
    return if (number != null) {
        number
    } else {
        val operation = monkeys[monkey]!!
        val subMonkeys = operation.split(" + ", " - ", " / ", " * ")
        val value = when {
            "+" in operation -> eval(subMonkeys[0], monkeys, humn) + eval(subMonkeys[1], monkeys, humn)
            "-" in operation -> eval(subMonkeys[0], monkeys, humn) - eval(subMonkeys[1], monkeys, humn)
            "/" in operation -> eval(subMonkeys[0], monkeys, humn) / eval(subMonkeys[1], monkeys, humn)
            "*" in operation -> eval(subMonkeys[0], monkeys, humn) * eval(subMonkeys[1], monkeys, humn)
            else -> throw IllegalStateException()
        }
        value
    }
}