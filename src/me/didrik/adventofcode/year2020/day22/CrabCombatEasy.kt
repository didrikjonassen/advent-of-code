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

    while (player1Deck.isNotEmpty() && player2Deck.isNotEmpty()) {
        val p1 = player1Deck.removeFirst()
        val p2 = player2Deck.removeFirst()
        if (p1 > p2) {
            player1Deck += listOf(p1, p2)
        } else {
            player2Deck += listOf(p2, p1)
        }
    }

    var answer = 0L
    val winnerDeck = ArrayDeque(player1Deck + player2Deck)
    while (winnerDeck.isNotEmpty()) {
        answer += winnerDeck.size * winnerDeck.removeFirst()
    }
    solve(answer)
}