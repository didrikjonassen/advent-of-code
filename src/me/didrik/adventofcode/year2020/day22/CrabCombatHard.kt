package me.didrik.adventofcode.year2020.day22

import me.didrik.adventofcode.solve
import java.io.File

@ExperimentalStdlibApi
fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day22/CrabCombatInput.txt")
    val input = file.readLines()

    val player1Deck = ArrayDeque(input.drop(1)
            .takeWhile { it.isNotEmpty() }
            .map { it.toInt() })

    val player2Deck = ArrayDeque(input
            .dropWhile { it.isNotEmpty() }
            .drop(2)
            .map { it.toInt() })

    recursiveGame(player1Deck, player2Deck)

    var answer = 0L
    val winnerDeck = ArrayDeque(player1Deck + player2Deck)
    while (winnerDeck.isNotEmpty()) {
        answer += winnerDeck.size * winnerDeck.removeFirst()
    }
    solve(answer)
}

@ExperimentalStdlibApi
private fun recursiveGame(p1Deck: ArrayDeque<Int>, p2Deck: ArrayDeque<Int>): Boolean {
    val seenStates = mutableSetOf<List<Int>>()

    do {
        val state = state(p1Deck, p2Deck)
        if (state in seenStates) {
            return true
        }
        seenStates += state

        val p1 = p1Deck.removeFirst()
        val p2 = p2Deck.removeFirst()
        if (p1Deck.size >= p1 && p2Deck.size >= p2) {
            if (recursiveGame(ArrayDeque(p1Deck.take(p1)), ArrayDeque(p2Deck.take(p2)))) {
                p1Deck += listOf(p1, p2)
            } else {
                p2Deck += listOf(p2, p1)
            }
        } else {
            if (p1 > p2) {
                p1Deck += listOf(p1, p2)
            } else {
                p2Deck += listOf(p2, p1)
            }
        }
    } while (p1Deck.isNotEmpty() && p2Deck.isNotEmpty())

    return p2Deck.isEmpty()
}

@ExperimentalStdlibApi
private fun state(p1Deck: ArrayDeque<Int>, p2Deck: ArrayDeque<Int>) =
        p1Deck + 0 + p2Deck