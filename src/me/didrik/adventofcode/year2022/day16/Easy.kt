package me.didrik.adventofcode.year2022.day16

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day16/Input.txt")
    val input = file.readLines()
    val valves = input.associate {
        it.substringAfter("Valve ").split(" ").first() to
                Valve(
                    it.substringAfter("rate=").split(";").first().toInt(),
                    it.split("""leads? to valves? """.toRegex()).last().split(", ")
                )
    }
    val distances = valves.values.associateWith { openFrom(it, valves) }
    solve(traverse(valves["AA"]!!, distances, setOf(), 30, 0))
}

data class Valve(
    val flow: Int,
    val neighbours: List<String>
)

private fun openFrom(valve: Valve, valves: Map<String, Valve>): List<Pair<Valve, Int>> {
    val queue = ArrayDeque(valve.neighbours.map { valves[it]!! to 2 })
    val distances = mutableMapOf(valve to 1)
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        distances += current
        current.first.neighbours.forEach {
            if (valves[it] !in distances.keys) {
                queue += valves[it]!! to current.second + 1
            }
        }
    }
    return distances.toList()
}

private fun traverse(valve: Valve, distances: Map<Valve, List<Pair<Valve, Int>>>, opened: Set<Valve>, time: Int, total: Int): Int {
    return if (time <= 0) {
        total
    } else {
        distances[valve]!!.filter { it.first.flow > 0 }.filter { it.first !in opened }.maxOfOrNull {
            traverse(it.first, distances, opened + it.first, time - it.second, total + valve.flow * time)
        } ?: (total + valve.flow * time)
    }
}