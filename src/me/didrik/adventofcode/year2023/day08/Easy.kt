package me.didrik.adventofcode.year2023.day08

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day08/Input.txt")
    val input = file.readLines()
    val directions = input.first()
    val nodes = input.drop(2).associate {
        val (pos, edges) = it.split(" = ")
        val (left, right) = edges.removeSurrounding("(", ")").split(", ")
        pos to (left to right)
    }

    var node = "AAA"
    var steps = 0
    while (node != "ZZZ") {
        node = if (directions[steps++ % directions.length] == 'L') nodes[node]!!.first else nodes[node]!!.second
    }
    solve(steps)
}
