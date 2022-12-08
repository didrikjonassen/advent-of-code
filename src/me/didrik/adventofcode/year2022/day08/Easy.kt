package me.didrik.adventofcode.year2022.day08

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day08/Input.txt")
    val input = file.readLines()
    val width = input.first().length
    val visible = Array(input.size) { BooleanArray(width) }
    visible[0].indices.forEach { visible.first()[it] = true }
    visible[0].indices.forEach { visible.last()[it] = true }
    visible.indices.forEach { visible[it][0] = true }
    visible.indices.forEach { visible[it][width - 1] = true }

    input.forEachIndexed { index, line ->
        var max = line.first()
        for (i in 1..width - 2) {
            if (line[i] > max) {
                max = line[i]
                visible[index][i] = true
            }
        }

        max = line.last()
        for (i in (1 .. width - 2).reversed()) {
            if (line[i] > max) {
                max = line[i]
                visible[index][i] = true
            }
        }
    }

    (1 .. input.first().length - 2).forEach { i ->
        var max = input[0][i]
        (1 .. input.size - 2).forEach { k ->
            if (input[k][i] > max) {
                max = input[k][i]
                visible[k][i] = true
            }
        }

        max = input[input.size-1][i]
        (1 .. input.size -2).reversed().forEach { k ->
            if (input[k][i] > max) {
                max = input[k][i]
                visible[k][i] = true
            }
        }
    }

    val visibleTrees = visible.flatMap { it.toList() }.count { it }
    solve(visibleTrees)
}