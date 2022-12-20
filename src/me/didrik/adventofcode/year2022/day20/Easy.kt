package me.didrik.adventofcode.year2022.day20

import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.abs

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day20/Input.txt")
    val input = file.readLines()
        .map { it.toInt() }
        .map { Node(it) }
    input.windowed(2, 1) {
        it[0].next = it[1]
        it[1].prev = it[0]

    }
    input.first().prev = input.last()
    input.last().next = input.first()
    val size = input.size - 1 // Number of moves to be back in the same position
    val halfSize = size / 2
    input.forEach { node ->
        var number = node.number % size
        if (number > halfSize) number -= size
        else if (number < -halfSize) number += size
        if (number > 0) repeat(number) { node.moveRight() }
        else repeat(abs(number)) { node.moveLeft() }
    }
    var pos = input.first { it.number == 0 }
    var sum = 0
    repeat(3) {
        repeat(1000) {
            pos = pos.next
        }
        sum += pos.number
    }
    solve(sum)
}

private data class Node(
    val number: Int
) {
    lateinit var prev: Node
    lateinit var next: Node

    fun moveRight() {
        prev.next = next
        next.prev = prev
        prev = next
        next = next.next
        next.prev = this
        prev.next = this
    }

    fun moveLeft() {
        prev.next = next
        next.prev = prev
        next = prev
        prev = prev.prev
        next.prev = this
        prev.next = this
    }
}