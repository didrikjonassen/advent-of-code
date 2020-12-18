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
        when (expression[pos++]) {
            ')' -> return Pair(value, pos)
            '*' -> {
                val (number, newPos) = multiplyWith(expression, pos)
                value *= number
                pos = newPos
            }
            '+' ->{
                val (number, newPos) = getNumber(expression, pos)
                value += number
                pos = newPos
            }
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

private fun multiplyWith(expression: String, position: Int): Pair<Long, Int> {
    var (value, pos) = getNumber(expression, position)
    while (pos < expression.length && expression[pos] == '+') {
        val (number, newPos) = getNumber(expression, pos + 1)
        value += number
        pos = newPos
    }
    return Pair(value, pos)
}