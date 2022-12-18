package me.didrik.adventofcode.year2022.day18

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day18/Input.txt")
    val input = file.readLines()
    val neighbours = (-1..1).flatMap { i ->
        (-1 .. 1).flatMap { j ->
            (-1 .. 1).map { k ->
                Triple(i, j, k)
            }
        }
    }.filter { listOf(it.first, it.second, it.third).count { it == 0 } == 2 }
    val cubes = input.map { it.split(",").map { it.toInt() } }
        .map { (first, second, third) -> Triple(first, second, third) }
    val result = cubes.sumOf { cube ->
        neighbours.map { it + cube }
            .count { it !in cubes }
    }
    solve(result)
}

private operator fun Triple<Int, Int, Int>.plus(other: Triple<Int, Int, Int>): Triple<Int, Int, Int> {
    return Triple(first + other.first, second + other.second, third + other.third)
}