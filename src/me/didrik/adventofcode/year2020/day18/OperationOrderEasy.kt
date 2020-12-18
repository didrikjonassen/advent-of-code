package me.didrik.adventofcode.year2020.day18

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day18/OperationOrderInput.txt")
    val input = file.readLines()

    val answer = input
            .map { it.replace(" ", "") }
            .map { doHomework(it, 0).first }
            .sum()
    solve(answer)
}

private fun doHomework(expression: String, position: Int): Pair<Long, Int> {
    var (value, pos) = getNumber(expression, position)
    while (pos < expression.length) {
        val operation = expression[pos++]
        if (operation == ')') return Pair(value, pos)
        val (number, newPos) = getNumber(expression, pos)
        pos = newPos
        when (operation) {
            '*' -> value *= number
            '+' -> value += number
        }
    }
    return Pair(value, pos)
}

private fun getNumber(expression: String, position: Int): Pair<Long, Int> {
    return if (expression[position] == '(')
        doHomework(expression, position + 1)
    else
        Pair(expression[position].toString().toLong(), position + 1)
}
