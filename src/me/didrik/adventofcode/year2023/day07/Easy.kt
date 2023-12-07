package me.didrik.adventofcode.year2023.day07

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day07/Input.txt")
    val winnings = file.readLines()
        .map { it.split(" ") }
        .map { Hand(it[0].toCards(), it[1].toLong()) }
        .sorted()
        .mapIndexed {index, hand ->
            (index + 1).toLong() * hand.bid
        }.sum()

    solve(winnings)
}

private fun String.toCards() = map { Card.valueOf(it.toString()) }

private data class Hand(
    val cards: List<Card>,
    val bid: Long
): Comparable<Hand> {
    override fun compareTo(other: Hand): Int {
        val thisType = this.type()
        val otherType = other.type()
        return - if (thisType != otherType) {
            thisType.compareTo(otherType)
        } else {
            this.cards.zip(other.cards)
                .filter { it.first != it.second }
                .map { it.first.compareTo(it.second) }
                .first()
        }
    }

    private fun type(): Type {
        val groups = cards.groupBy { it }
        return when {
            groups.size == 1 -> Type.femLike
            groups.values.any { it.size == 4 } -> Type.fireLike
            groups.size == 2 -> Type.hus
            groups.values.any { it.size == 3 } -> Type.treLike
            groups.values.count { it.size == 2 } == 2 -> Type.toPar
            groups.values.count { it.size == 2 } == 1 -> Type.par
            else -> Type.ingen
        }
    }
}

private enum class Card {
    A, K, Q, J, T, `9`, `8`, `7`, `6`, `5`, `4`, `3`, `2`
}

private enum class Type {
    femLike, fireLike, hus, treLike, toPar, par, ingen
}