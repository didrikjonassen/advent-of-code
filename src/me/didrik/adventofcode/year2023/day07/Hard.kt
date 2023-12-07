package me.didrik.adventofcode.year2023.day07

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day07/Input.txt")
    val winnings = file.readLines()
        .map { it.split(" ") }
        .map { HardHand(it[0].toCards(), it[1].toLong()) }
        .sorted()
        .mapIndexed {index, hand ->
            (index + 1).toLong() * hand.bid
        }.sum()

    solve(winnings)
}

private fun String.toCards() = map { HardCard.valueOf(it.toString()) }

private data class HardHand(
    val cards: List<HardCard>,
    val bid: Long
): Comparable<HardHand> {
    override fun compareTo(other: HardHand): Int {
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

    private fun type(): HardType {
        val groups = cards.filter { it != HardCard.J }.groupBy { it }
        return when {
            groups.size in 0..1 -> HardType.femLike
            groups.size == 2 && groups.values.any { it.size == 1 } -> HardType.fireLike
            groups.size == 2 -> HardType.hus
            groups.size == 5 -> HardType.ingen
            groups.size == 4 -> HardType.par
            groups.values.maxOf { it.size } + cards.count { it == HardCard.J } == 3 -> HardType.treLike
            else -> HardType.toPar
        }
    }
}

private enum class HardCard {
    A, K, Q, T, `9`, `8`, `7`, `6`, `5`, `4`, `3`, `2`, J
}

private enum class HardType {
    femLike, fireLike, hus, treLike, toPar, par, ingen
}