package me.didrik.adventofcode.year2022.day12

import me.didrik.adventofcode.solve
import java.io.File
import java.util.*
import kotlin.system.exitProcess

val neighbors = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day12/Input.txt")
    val input = file.readLines()
    val grid = Array(input.size + 2) { CharArray(input[0].length + 2)}
    grid.first().indices.forEach { grid.first()[it] = 'F'; grid.last()[it] = 'F' }
    input.forEachIndexed { index, s ->
        grid[index + 1] = "F${s}F".toCharArray()
    }
    val distances = Array(grid.size) { Array<Node?>(grid[0].size) { null } }
    val queue = LinkedList<Node>()
    (grid.indices).forEach { i ->
        grid[0].indices.forEach { k ->
            if (grid[i][k] == 'S') {
                distances[i][k] = Node(i, k, 'a', 0)
                queue += distances[i][k]!!
            }
        }
    }
    while (queue.isNotEmpty()) {
        val node = queue.pop()
        neighbors.forEach {
            val x = node.x + it.first
            val y = node.y + it.second
            if (grid[x][y] == 'E' && node.height >= 'y') {
                solve(node.distance + 1)
                exitProcess(0);
            } else if (grid[x][y] != 'F' && distances[x][y] == null && grid[x][y] <= node.height + 1) {
                val newNode = Node(x, y, grid[x][y], node.distance + 1)
                queue += newNode
                distances[x][y] = newNode
            }
        }
    }
}

data class Node(
    val x: Int,
    val y: Int,
    val height: Char,
    val distance: Int
)