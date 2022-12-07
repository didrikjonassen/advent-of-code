package me.didrik.adventofcode.year2022.day06

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day06/Input.txt")
    val input = file.readLines().first()
    val result = input.windowed(4, 1)
        .mapIndexed { index, s ->
            index to s.chars().distinct().count()
        }.first { it.second == 4L }
        .first + 4
    solve(result)
}