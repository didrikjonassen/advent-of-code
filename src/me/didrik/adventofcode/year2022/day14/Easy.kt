package me.didrik.adventofcode.year2022.day14

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.system.exitProcess

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day14/Input.txt")
    val input = file.readLines().map { it.split(" -> ") }
    val grid = Array(700) { BooleanArray(200)}
    for (path in input) {
        for (i in 0 until path.size - 1) {
            val (fromx, fromy) = path[i].split(",").map { it.toInt() }
            val (tox, toy) = path[i + 1].split(",").map { it.toInt() }
            if (fromx == tox) {
                for (k in min(fromy, toy)..max(fromy, toy)) {
                    grid[fromx][k] = true
                }
            } else {
                for (k in min(fromx, tox)..max(fromx, tox)) {
                    grid[k][fromy] = true
                }
            }
        }
    }
    val flows = listOf(0 to 1, -1 to 1, 1 to 1)
    var sands = 0
    while (true) {
        var (posx, posy) = 500 to 0
        while (flows.any { !grid[posx + it.first][posy + it.second] }) {
            val newPos = flows.map { posx + it.first to posy + it.second }
                .first { !grid[it.first][it.second] }
            if (newPos.second == 199) {
                solve(sands)
                exitProcess(0)
            }
            posx = newPos.first
            posy = newPos.second
        }
        ++sands
        grid[posx][posy] = true
    }
}