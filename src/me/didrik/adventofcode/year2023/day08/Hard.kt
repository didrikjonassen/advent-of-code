package me.didrik.adventofcode.year2023.day08

import me.didrik.adventofcode.solve
import java.io.File
import java.math.BigInteger

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day08/Input.txt")
    val input = file.readLines()
    val directions = input.first()
    val nodes = input.drop(2).associate {
        val (pos, edges) = it.split(" = ")
        val (left, right) = edges.removeSurrounding("(", ")").split(", ")
        pos to (left to right)
    }

    // Works because the input is crafted to make it work
    val steps = nodes.map { it.key }
        .filter { it.endsWith("A") }
        .map {
            var steps = 0L
            var node = it
            while (!node.endsWith("Z")) {
                node = if (directions[(steps++ % directions.length).toInt()] == 'L') nodes[node]!!.first else nodes[node]!!.second
            }
            BigInteger.valueOf(steps)
        }.reduce {a, b ->
            a * b / a.gcd(b)
        }
    solve(steps)
}
