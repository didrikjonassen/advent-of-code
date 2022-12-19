import me.didrik.adventofcode.solve
import java.io.File
import kotlin.math.ceil
import kotlin.math.max

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day19/Input.txt")
    val qualityLevel = file.readLines()
        .asSequence()
        .take(3)
        .map { it.substringAfter(": ") }
        .map { it.split(" ").mapNotNull { it.toIntOrNull() } }
        .map { dfs(it, 0, 0, 0, 0, 1, 0, 0, 0, 0) }
        .reduce(Math::multiplyExact)
    solve(qualityLevel)
}

private const val endTime = 32

private fun dfs(costs: List<Int>, ores: Int, clay: Int, obsidian: Int, geodes: Int, oreBots: Int, clayBots: Int, obsidianBots: Int, geodeBots: Int, minutes: Int): Int {
    if (minutes > endTime) {
        return 0
    }
    if (minutes == endTime) {
        return geodes
    }
    return listOf(
        if (geodeBots > 0) {
            val wait = endTime - minutes
            dfs(costs,
                ores + oreBots * wait,
                clay + clayBots * wait,
                obsidian + obsidianBots * wait,
                geodes + geodeBots * wait,
                oreBots,
                clayBots,
                obsidianBots,
                geodeBots,
                minutes + wait)
        } else 0,
        if (oreBots < listOf(costs[0], costs[1], costs[2], costs[4]).maxOrNull()!!) {
            val wait = ceil(max(costs[0] - ores, 0).toDouble()/oreBots).toInt() + 1
            dfs(
                costs,
                ores + oreBots * wait - costs[0],
                clay + clayBots * wait,
                obsidian + obsidianBots * wait,
                geodes + geodeBots * wait,
                oreBots + 1,
                clayBots,
                obsidianBots,
                geodeBots,
                minutes + wait
            )
        } else 0,
        if(clayBots < costs[3]) {
            val wait = ceil(max(costs[1] - ores, 0).toDouble()/oreBots).toInt() + 1
            dfs(
                costs,
                ores + oreBots * wait - costs[1],
                clay + clayBots * wait,
                obsidian + obsidianBots * wait,
                geodes + geodeBots * wait,
                oreBots,
                clayBots + 1,
                obsidianBots,
                geodeBots,
                minutes + wait
            )
        } else 0,
        if(clayBots > 0) {
            val wait = ceil(max( max(costs[2] - ores, 0).toDouble()/oreBots, max(costs[3] - clay, 0).toDouble()/clayBots)).toInt() + 1
            dfs(
                costs,
                ores + oreBots * wait - costs[2],
                clay + clayBots * wait - costs[3],
                obsidian + obsidianBots * wait,
                geodes + geodeBots * wait,
                oreBots,
                clayBots,
                obsidianBots + 1,
                geodeBots,
                minutes + wait
            )
        } else 0,
        if(obsidianBots > 0) {
            val wait = ceil(max( max(costs[4] - ores, 0).toDouble()/oreBots, max(costs[5] - obsidian, 0).toDouble()/obsidianBots)).toInt() + 1
            dfs(
                costs,
                ores + oreBots * wait - costs[4],
                clay + clayBots * wait,
                obsidian + obsidianBots * wait - costs[5],
                geodes + geodeBots * wait,
                oreBots,
                clayBots,
                obsidianBots,
                geodeBots + 1,
                minutes + wait
            )
        } else 0,
    ).maxOrNull()!!
}