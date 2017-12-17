package me.didrik.adventofcode.year2017.day3

import java.io.File

fun main(args: Array<String>) {
    val input = Integer.parseInt(File("src/me/didrik/adventofcode/year2017/day3/SpiralMemoryInput.txt").readText())
    val n = 11
    val array = Array(n, { IntArray(n) })
    val mid = n / 2

    array[mid][mid] = 1
    var layer = 1

    while (true) {
        (mid - layer + 1..mid + layer)
                .forEach { i ->
                    array[i][mid + layer] = array[i - 1][mid + layer] + (i - 1..i + 1).map { array[it][mid + layer - 1] }.sum()
                    if (array[i][mid + layer] > input) {
                        println(array[i][mid + layer])
                        System.exit(0)
                    }
                }
        (mid + layer - 1 downTo mid - layer)
                .forEach { i ->
                    array[mid + layer][i] = array[mid + layer][i + 1] + (i + 1 downTo i - 1).map { array[mid + layer - 1][it] }.sum()
                    if (array[mid + layer][i] > input) {
                        println(array[mid + layer][i])
                        System.exit(0)
                    }
                }
        (mid + layer - 1 downTo mid - layer)
                .forEach { i ->
                    array[i][mid - layer] = array[i + 1][mid - layer] + (i + 1 downTo i - 1).map { array[it][mid - layer + 1] }.sum()
                    if (array[i][mid - layer] > input) {
                        println(array[i][mid - layer])
                        System.exit(0)
                    }
                }
        (mid - layer + 1..mid + layer)
                .forEach { i ->
                    array[mid - layer][i] = array[mid - layer][i - 1] + (i - 1..i + 1).map { array[mid - layer + 1][it] }.sum()
                    if (array[mid - layer][i] > input) {
                        println(array[mid - layer][i])
                        System.exit(0)
                    }
                }
        layer++
    }

}

