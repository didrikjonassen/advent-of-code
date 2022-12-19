package me.didrik.adventofcode.year2022.day19

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day19/Input.txt")
    val qualityLevel = file.readLines()
        .asSequence()
        .map { it.substringAfter(": ") }
        .map { it.split(" ").mapNotNull { it.toIntOrNull() } }
        .onEach { println(it) }.map { dfs(it, 0, 0, 0, 0, 1, 0, 0, 0, 0) }
        .onEach { println(it) }
        .mapIndexed { index, geodes -> (index + 1) * geodes }.sum()
    solve(qualityLevel)
}

private fun dfs(costs: List<Int>, ores: Int, clay: Int, obsidian: Int, geodes: Int, oreBots: Int, clayBots: Int, obsidianBots: Int, geodeBots: Int, minutes: Int): Int {
    if (minutes == 24) {
        return geodes
    }
    return listOf(
        if (ores < listOf(costs[0], costs[1], costs[2], costs[4]).maxOrNull()!! || clay < costs[3] || obsidian < costs[5]) dfs(
            costs, ores + oreBots, clay + clayBots, obsidian + obsidianBots, geodes + geodeBots, oreBots, clayBots, obsidianBots, geodeBots, minutes + 1
        ) else 0,
        if (ores >= costs[0] && oreBots < listOf(costs[0], costs[1], costs[2], costs[4]).maxOrNull()!!) dfs(
            costs, ores + oreBots - costs[0], clay + clayBots, obsidian + obsidianBots, geodes + geodeBots, oreBots + 1, clayBots, obsidianBots, geodeBots, minutes + 1
        ) else 0,
        if (ores >= costs[1]) dfs(
            costs, ores + oreBots - costs[1], clay + clayBots, obsidian + obsidianBots, geodes + geodeBots, oreBots, clayBots + 1, obsidianBots, geodeBots, minutes + 1
        ) else 0,
        if (ores >= costs[2] && clay >= costs[3]) dfs(
            costs, ores + oreBots - costs[2], clay + clayBots - costs[3], obsidian + obsidianBots, geodes + geodeBots, oreBots, clayBots, obsidianBots + 1, geodeBots, minutes + 1
        ) else 0,
        if (ores >= costs[4] && obsidian >= costs[5]) dfs(
            costs, ores + oreBots - costs[4], clay + clayBots, obsidian + obsidianBots - costs[5], geodes + geodeBots, oreBots, clayBots, obsidianBots, geodeBots + 1, minutes + 1
        ) else 0,
    ).maxOrNull()!!
}