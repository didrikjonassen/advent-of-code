package `2`

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/2/CorruptionChecksumInput.txt")
    val input = file.readLines()
    val parsedInput = input.map { row -> row.split('\t').map { numberAsText -> numberAsText.toInt() } }
    val sum = parsedInput.map { row -> row.max()!! - row.min()!! }.sum()
    println("sum = $sum")
}