package me.didrik.adventofcode.year2022.day05

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day05/Input.txt")
    val input = file.readLines()
    val stackInput = input.takeWhile { it.isNotEmpty() }.reversed()
    val size = stackInput.first().trim().last().toString().toInt()
    val stacks = Array<MutableList<Char>>(size + 1) {ArrayList()}
    stackInput.drop(1).forEach {
        (1 until  it.length step 4).forEachIndexed { index, i ->
            if (it[i] != ' ')
                stacks[index + 1] += it[i]
        }
    }
    val regex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    val moves = input.dropWhile { it.isNotEmpty() }
        .drop(1)
    moves.forEach {
        val (count, from, to) = regex.matchEntire(it)!!.destructured
        stacks[to.toInt()] += stacks[from.toInt()].takeLast(count.toInt())
        repeat(count.toInt()) {
            stacks[from.toInt()].removeLast()
        }
    }
    val result = stacks.mapNotNull { it.lastOrNull() }
        .joinToString("")
    solve(result)
}