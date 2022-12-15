package me.didrik.adventofcode.year2022.day15

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.abs

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day15/Input.txt")
    val result = file.readLines().map {
        """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""".toRegex().matchEntire(it)!!.groupValues.drop(1).map { it.toInt() }
    }.flatMapTo(HashSet()) { (sensorX, sensorY, beakonX, beakonY) ->
        val distanceToBeakon = abs(sensorX - beakonX) + abs(sensorY - beakonY)
        val distanceToY = abs(sensorY - 2000000)
        val distanceLeftOver = distanceToBeakon - distanceToY
        IntRange(sensorX - distanceLeftOver, sensorX + distanceLeftOver)
    }.size - 1
    solve(result)
}