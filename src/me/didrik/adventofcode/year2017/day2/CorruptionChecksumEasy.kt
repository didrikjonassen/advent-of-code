package me.didrik.adventofcode.year2017.day2

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/me/didrik/adventofcode/year2017/day2/CorruptionChecksumInput.txt")
    val input = file.readLines()
    val parsedInput = input.map { row -> row.split('\t').map { numberAsText -> numberAsText.toInt() } }
    val sum = parsedInput.map { row -> row.max()!! - row.min()!! }.sum()
    println("sum = $sum")
}