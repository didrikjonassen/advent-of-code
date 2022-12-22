package me.didrik.adventofcode.year2022.day21

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day21/Input.txt")
    val monkeys = file.readLines()
        .map { it.split(": ") }
        .associateTo(HashMap()) { it[0] to it[1] }
    val root = eval("root", monkeys)
    solve(root)
}

private fun eval(monkey: String, monkeys: MutableMap<String, String>): Long {
    val number = monkeys[monkey]?.toLongOrNull()
    return if (number != null) {
        number
    } else {
        val operation = monkeys[monkey]!!
        val subMonkeys = operation.split(" + ", " - ", " / ", " * ")
        val value = when {
            "+" in operation -> eval(subMonkeys[0], monkeys) + eval(subMonkeys[1], monkeys)
            "-" in operation -> eval(subMonkeys[0], monkeys) - eval(subMonkeys[1], monkeys)
            "/" in operation -> eval(subMonkeys[0], monkeys) / eval(subMonkeys[1], monkeys)
            "*" in operation -> eval(subMonkeys[0], monkeys) * eval(subMonkeys[1], monkeys)
            else -> throw IllegalStateException()
        }
        monkeys[monkey] = value.toString()
        value
    }
}