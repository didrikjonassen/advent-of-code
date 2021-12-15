package me.didrik.adventofcode.year2021.day15

import me.didrik.adventofcode.solve
import java.io.File
import java.util.*

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day15/Input.txt")
    val input = file.readLines()

    val n = input.size
    val m = input[0].length
    val costs = Array(n + 2) { IntArray(m + 2) }
    val visited = Array(n + 2) { BooleanArray(m + 2) { true } }

    for (i in 1..n) {
        for (k in 1..m) {
            visited[i][k] = false
            costs[i][k] = input[i - 1][k - 1] - '0'
        }
    }

    val heap = PriorityQueue<Node>()

    var node = Node(0, 1, 1)
    while (node.posX != n || node.posY != m) {
        visited[node.posX][node.posY] = true
        listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1).forEach { (x, y) ->
            if (!visited[node.posX + x][node.posY + y]) {
                heap += Node(node.totalCost + costs[node.posX + x][node.posY + y], node.posX + x, node.posY + y)
            }
        }
        node = heap.poll()
    }
    solve(node.totalCost)
}

private class Node(
    val totalCost: Int,
    val posX: Int,
    val posY: Int
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return totalCost.compareTo(other.totalCost)
    }
}