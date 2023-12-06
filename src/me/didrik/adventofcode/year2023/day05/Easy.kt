package me.didrik.adventofcode.year2023.day05

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day05/Input.txt")
    val input = file.readLines()
    val seeds = input.first().split(": ")[1].split(" ").map { it.toLong() }
    val seed = input.dropWhile { it != "seed-to-soil map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()
    val soil = input.dropWhile { it != "soil-to-fertilizer map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()
    val fertilizer = input.dropWhile { it != "fertilizer-to-water map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()
    val water = input.dropWhile { it != "water-to-light map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()
    val light = input.dropWhile { it != "light-to-temperature map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()
    val temperature = input.dropWhile { it != "temperature-to-humidity map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()
    val humidity = input.dropWhile { it != "humidity-to-location map:" }
        .drop(1)
        .takeWhile { it.isNotEmpty() }
        .toSearch()

    val closest = seeds.minOfOrNull {
        humidity[temperature[light[water[fertilizer[soil[seed[it]]]]]]]
    }
    solve(closest)

}

private fun List<String>.toSearch(): BinarySearch {
    val list = arrayListOf<Triple<Long, Long, Long>>()
    this.forEach {
        val (destination, source, range) = it.split(" ").map { it.toLong() }
        list += Triple(source, source + range, destination)
    }
    return BinarySearch(list.sortedBy { it.first }.map { it.first until it.second to it.third })
}

private class BinarySearch(
    val sortedValues: List<Pair<LongRange, Long>>
) {
    operator fun get(long: Long): Long {
        val pos = sortedValues.binarySearch {
            if (long in (it.first)) {
                0
            } else if (it.first.first < long) {
                -1
            } else {
                1
            }
        }
        if (pos < 0) {
            return long
        }
        val pair = sortedValues[pos]
        return pair.second + (long - pair.first.first)
    }
}
