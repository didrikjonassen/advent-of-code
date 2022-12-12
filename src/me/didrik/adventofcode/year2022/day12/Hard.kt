package me.didrik.adventofcode.year2022.day12

import me.didrik.adventofcode.solve
import java.io.File
import java.util.*
import kotlin.system.exitProcess

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day12/Input.txt")
    val input = file.readLines()
    val grid = Array(input.size + 2) { CharArray(input[0].length + 2)}
    grid.first().indices.forEach { grid.first()[it] = 'F'; grid.last()[it] = 'F' }
    input.forEachIndexed { index, s ->
        grid[index + 1] = "F${s}F".toCharArray()
    }
    val distances = Array(grid.size) { Array<Node2?>(grid[0].size) { null } }
    val queue = LinkedList<Node2>()
    (grid.indices).forEach { i ->
        grid[0].indices.forEach { k ->
            if (grid[i][k] == 'E') {
                distances[i][k] = Node2(i, k, 'z', 0)
                queue += distances[i][k]!!
            }
        }
    }
    while (queue.isNotEmpty()) {
        val node = queue.pop()
        neighbors.forEach {
            val x = node.x + it.first
            val y = node.y + it.second
            if ((grid[x][y] == 'a' || grid[x][y] == 'S') && node.height == 'b') {
                solve(node.distance + 1)
                exitProcess(0);
            } else if (grid[x][y] != 'F' && distances[x][y] == null && grid[x][y] >= node.height - 1) {
                val newNode = Node2(x, y, grid[x][y], node.distance + 1)
                queue += newNode
                distances[x][y] = newNode
            }
        }
    }
}

data class Node2(
    val x: Int,
    val y: Int,
    val height: Char,
    val distance: Int
)