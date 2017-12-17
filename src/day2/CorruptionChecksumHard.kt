package day2

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/day2/CorruptionChecksumInput.txt")
    val input = file.readLines()
    val parsedInput = input.map { row -> row.split('\t').map { numberAsText -> numberAsText.toInt() } }
    val sum = parsedInput.map { row -> row.flatMap { element -> row.map { Pair(element, it) } }.filter { (first, second) -> first != second && first % second == 0 }.sumBy { (first, second) -> first / second } }.sumBy { it }
    println("sum = $sum")
}