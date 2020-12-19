package me.didrik.adventofcode.year2020.day19

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day19/MonsterMessagesInput.txt")
    val input = file.readLines()

    val incompleteRules = mutableMapOf<String, String>()
    val completedRules = mutableMapOf<String, String>()

    input.takeWhile { it.isNotEmpty() }
            .map { it.split(": ") }
            .forEach {
                if (it[1].startsWith("\"")) {
                    completedRules[it[0]] = it[1][1].toString()
                } else {
                    incompleteRules[it[0]] = it[1]
                }
            }

    while (incompleteRules.isNotEmpty()) {
        for ((ruleNumber, rule) in incompleteRules) {
            val canBeCompleted = rule.split(" ")
                    .filterNot { it == "|" }
                    .all { it in completedRules }
            if (canBeCompleted) {
                completedRules[ruleNumber] = rule.split(" ")
                        .map { if (it == "|") "|" else completedRules[it] }
                        .joinToString(separator = "", prefix = "(", postfix = ")")
            }
        }
        incompleteRules -= completedRules.keys
    }

    val theOneRule = completedRules["0"]!!.toRegex()

    val validMessages = input.dropWhile { it.isNotEmpty() }
            .drop(1)
            .filter { theOneRule.matches(it) }
            .count()

    solve(validMessages)
}