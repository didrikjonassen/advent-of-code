package me.didrik.adventofcode.year2018.day02

import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2018/day02/InventoryManagementSystemInput.txt")
    val input = file.readLines().map { it.toList().sorted() + 'å' }
    var twos = 0
    var threes = 0
    for (line in input) {
        if (line.containsEqualCharacters(2)) twos++
        if (line.containsEqualCharacters(3)) threes++
    }
    println("Checksum=${twos * threes}")
}

private fun List<Char>.containsEqualCharacters(n: Int): Boolean {
    for (i in 0 until size - n) {
        if (this[i] == this[i + n - 1] && this[i] != this[i + n]) {
            return true
        }
    }
    return false
}