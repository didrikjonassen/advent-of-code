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
                    .all { it in completedRules || it == ruleNumber }
            if (canBeCompleted) {
                if (ruleNumber == "11") {
                    // Recursive regex would be preferable, but alas.
                    completedRules[ruleNumber] = (1..10)
                            .joinToString(separator = "|", prefix = "(", postfix = ")") {
                                completedRules["42"]!!.repeat(it) + completedRules["31"]!!.repeat(it)
                            }
                } else {
                    completedRules[ruleNumber] = rule.split(" ")
                            .map { if (it == "|") "|" else completedRules[it] }
                            .joinToString(separator = "", prefix = "(", postfix = ")")
                    if (ruleNumber == "8") {
                        completedRules[ruleNumber] += "+"
                    }
                }
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