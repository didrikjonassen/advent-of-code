package me.didrik.adventofcode.year2017.day11

import java.io.File

fun main(args: Array<String>) {
    val path = File("src/me/didrik/adventofcode/year2017/day11/HexEdInput.txt").readText().split(',')
    val directionCount = path.groupingBy { it }.eachCount()
    println(directionCount)
    //check output. Remove opposite directions and have n, se and ne left
    val n = directionCount["n"]!!-directionCount["s"]!!
    val se = directionCount["se"]!!-directionCount["nw"]!!
    val ne = directionCount["ne"]!!-directionCount["sw"]!!
    // n + se = ne, and there are more of se than ne:
    println(se+ne)
}