package me.didrik.adventofcode.year2023.day01

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2023/day01/Input.txt")
    val input = file.readLines()
    val solution = input.map {
        val sb = StringBuilder()
        var i = 0
        while (i < it.length) {
            val substring = it.substring(i)
            when {
                it[i].isDigit() -> sb.append(it[i])
                substring.startsWith("one") -> {
                    sb.append("1")
                }

                substring.startsWith("two") -> {
                    sb.append("2")
                }

                substring.startsWith("three") -> {
                    sb.append("3")
                }

                substring.startsWith("four") -> {
                    sb.append("4")
                }

                substring.startsWith("five") -> {
                    sb.append("5")
                }

                substring.startsWith("six") -> {
                    sb.append("6")
                }

                substring.startsWith("seven") -> {
                    sb.append("7")
                }

                substring.startsWith("eight") -> {
                    sb.append("8")
                }

                substring.startsWith("nine") -> {
                    sb.append("9")
                }
            }
            ++i
        }
        sb.toString()
    }.sumOf { "${it.first()}${it.last()}".toInt() }


    solve(solution)
}