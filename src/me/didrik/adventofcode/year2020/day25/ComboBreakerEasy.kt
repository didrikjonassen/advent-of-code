package me.didrik.adventofcode.year2020.day25

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day25/ComboBreakerInput.txt")
    val input = file.readLines().map { it.toLong() }

    val (cardPublic, doorPublic) = input
    val cardIterations = iterations(cardPublic)
    val doorIterations = iterations(doorPublic)

    var answer = 1L
    if (cardIterations < doorIterations) {
        for (i in 1..cardIterations) {
            answer = transform(answer, doorPublic)
        }
    } else {
        for (i in 1..doorIterations) {
            answer = transform(answer, cardPublic)
        }
    }

    solve(answer)
}

private fun iterations(key: Long): Long {
    var transform = 1L
    var iterations = 0L
    while (transform != key) {
        transform = transform(transform, 7L)
        ++iterations
    }
    return iterations
}

private fun transform(value: Long, subjectNumber: Long) =
    value * subjectNumber % 20201227L