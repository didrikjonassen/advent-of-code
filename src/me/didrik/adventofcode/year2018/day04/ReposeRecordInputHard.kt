package me.didrik.adventofcode.year2018.day04

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val file = File("src/me/didrik/adventofcode/year2018/day04/ReposeRecordInput.txt")
    val input = file.readLines()
    val format = """\[([^]]+)] (.+)""".toRegex()
    val dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    val records = input.mapNotNull {
        format.matchEntire(it)?.destructured?.let { (timestamp, event) ->
            Record2(LocalDateTime.parse(timestamp, dateTimeFormat), event)
        }
    }.sorted()

    val sleepTimes = mutableMapOf<Int, IntArray>()
    lateinit var guard: IntArray
    var asleepMinute = 0
    for (record in records) {
        when {
            record.event.startsWith("Guard") -> {
                val guardNumber = """Guard #(\d+) begins shift""".toRegex().matchEntire(record.event)!!.groupValues[1].toInt()
                guard = sleepTimes.getOrPut(guardNumber) { IntArray(60) }
            }
            record.event.startsWith("wakes") ->
                for (i in asleepMinute until record.timestamp.minute) {
                    guard[i]++
                }
            record.event.startsWith("falls") ->
                asleepMinute = record.timestamp.minute
            else -> throw IllegalStateException()
        }
    }

    val mostConsistentSleeper = sleepTimes
            .mapValues {
                it.value
                        .mapIndexed { index, i -> Pair(index, i) }
                        .maxBy { it.second }!!
            }.map { it.toPair() }
            .maxBy { it.second.second }!!

    println(mostConsistentSleeper)
    println(mostConsistentSleeper.first * mostConsistentSleeper.second.first)
}

private data class Record2(
        val timestamp: LocalDateTime,
        val event: String
) : Comparable<Record2> {
    override fun compareTo(other: Record2) = timestamp.compareTo(other.timestamp)
}