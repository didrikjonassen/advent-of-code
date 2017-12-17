package day5

import java.io.File

fun main(args: Array<String>) {
    val offsets = File("src/day5/AMazeOfTwistyTrampolinesAllAlikeInput.txt").readLines().map { Integer.parseInt(it) }.toMutableList()
    var count = 0
    try {
        var pos = 0
        while (true) {
            val offset = offsets[pos]++
            count++
            pos += offset
        }
    } catch (e: IndexOutOfBoundsException) {
        println("count = $count")
    }
}