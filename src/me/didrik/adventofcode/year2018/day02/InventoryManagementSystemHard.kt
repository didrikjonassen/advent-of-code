package me.didrik.adventofcode.year2018.day02

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2018/day02/InventoryManagementSystemInput.txt")
    val input = file.readLines()
    for (i in 0 until input.size - 1) {
        for (k in i + 1 until input.size) {
            if (input[i].commonCharacters(input[k]).length == input[i].length - 1) {
                println("Common Characters=${input[i].commonCharacters(input[k])}")
            }
        }
    }
}

private fun String.commonCharacters(s: String) =
        this.zip(s)
                .filter { it.first == it.second }
                .joinToString(separator = "") { it.first.toString() }