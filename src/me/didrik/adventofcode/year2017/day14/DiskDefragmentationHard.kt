package me.didrik.adventofcode.year2017.day14

import me.didrik.adventofcode.year2017.day10.knotHash
import java.io.File

fun main(args: Array<String>) {
    val lengths = File("src/me/didrik/adventofcode/year2017/day14/DiskDefragmentationInput.txt").readText().toCharArray().map { it.toInt() }
    val grid = (0 until 128)
            .map { i ->
                knotHash((lengths + "-$i".toCharArray().map { it.toInt() }).toMutableList())
            }
            .map {
                it.map {
                    Integer.parseInt(it.toString(), 16)
                }.joinToString(separator = "") {
                    val binary = it.toString(2)
                    "0000".substring(binary.length) + binary
                }
            }
            .map { it.toCharArray() }
            .toTypedArray()

    var group = '2'
    while ('1' in grid.joinToString(separator = "") { it.joinToString(separator = "") }) {
        val index = grid.joinToString(separator = "") { it.joinToString(separator = "") }.indexOf('1')
        fillInGrid(index/128, index%128, grid, group++)
    }

    val groups = grid.joinToString(separator = "") { it.joinToString(separator = "") }
            .map { it }
            .filter { it != '0' }
            .distinct()
            .count()

    println("groups = $groups")
}

fun fillInGrid(i: Int, k: Int, grid: Array<CharArray>, group: Char) {
    try {
        if (grid[i][k] != '1') return
    } catch (e: ArrayIndexOutOfBoundsException) {
        return
    }
    grid[i][k] = group
    fillInGrid(i - 1, k, grid, group)
    fillInGrid(i + 1, k, grid, group)
    fillInGrid(i, k - 1, grid, group)
    fillInGrid(i, k + 1, grid, group)
}
