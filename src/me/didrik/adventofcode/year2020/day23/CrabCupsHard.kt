package me.didrik.adventofcode.year2020.day23

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day23/CrabCupsInput.txt")
    val n = 1_000_000
    val input = file.readLines()[0].toList().map { it.toString().toInt() } + (10..n)
    val cupList = input.map { Cup(it) }
    for (i in 0 until (cupList.size - 1)) {
        cupList[i].next = cupList[i + 1]
    }
    cupList.last().next = cupList.first()
    var current = cupList.first()
    val cups = cupList.associateBy { it.label }

    repeat(10_000_000) {
        val pickUp = current.pickUp()
        val pickUpValues = listOf(pickUp.label, pickUp.next.label, pickUp.next.next.label)
        val destValue = (1..4).map { current.label - it }.map { if (it <= 0) it + n else it }.filterNot { it in pickUpValues }.firstOrNull()!!
        val destination = cups[destValue]!!
        destination.place(pickUp)
        current = current.next
    }

    val cup1 = cups[1]!!
    solve(cup1.next.label.toLong() * cup1.next.next.label.toLong())
}

private data class Cup(
        val label: Int,
) {
    lateinit var next: Cup

    fun pickUp(): Cup {
        val pickedUp = next
        next = next.next.next.next
        return pickedUp
    }

    fun place(cup: Cup) {
        cup.next.next.next = next
        next = cup
    }
}
