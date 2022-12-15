package me.didrik.adventofcode.year2022.day15

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.abs

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day15/Input.txt")
    val possibleRange = 0..4000000
    val input = file.readLines().map {
        """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""".toRegex().matchEntire(it)!!.groupValues.drop(1).map { it.toInt() }
    }
    val sensors = input.map { (sensorX, sensorY, beakonX, beakonY) ->
        Triple(sensorX, sensorY, abs(sensorX - beakonX) + abs(sensorY - beakonY))
    }
    val distressBeakon = input.asSequence().map { (sensorX, sensorY, beakonX, beakonY) ->
        val distanceToPossibleDistressBeakon = abs(sensorX - beakonX) + abs(sensorY - beakonY) + 1
        (0..distanceToPossibleDistressBeakon).asSequence().flatMap {
            listOf(
                sensorX - distanceToPossibleDistressBeakon + it to sensorY + it,
                sensorX - distanceToPossibleDistressBeakon + it to sensorY - it,
                sensorX + distanceToPossibleDistressBeakon - it to sensorY + it,
                sensorX + distanceToPossibleDistressBeakon - it to sensorY - it
            )
        }.filter {
            it.first in possibleRange && it.second in possibleRange
        }.filter { sensors.all { sensor -> abs(sensor.first - it.first) + abs(sensor.second - it.second) > sensor.third } }
            .firstOrNull()
    }.filterNotNull().first()
    solve(distressBeakon.first * 4000000L + distressBeakon.second)
}