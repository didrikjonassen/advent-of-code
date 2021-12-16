package me.didrik.adventofcode.year2021.day16

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day16/Input.txt")
    val input = file.readLines()
    val bits = input.first().map { it.toString().toInt(16).toString(2) }.joinToString("") { "0000".substring(0, 4 - it.length) + it }

    solve(bits.parse(0).second)
}

private fun String.parse(startPos: Int): Pair<Int, Long> {
    var pos = startPos + 3
    val version = substring(startPos, pos).toInt(2)
    val type = substring(pos, pos + 3).toInt(2)
    pos += 3
    val state = when (type) {
        4 -> {
            val allBits = StringBuilder()
            do {
                val groupPrefix = this[pos++]
                allBits.append(substring(pos, pos + 4))
                pos += 4
            } while (groupPrefix == '1')
            Pair(pos, allBits.toString().toLong(2))
        }
        0 -> parseOperator(pos) { values -> values.sum() }
        1 -> parseOperator(pos) { values -> values.reduce(Long::times) }
        2 -> parseOperator(pos) { values -> values.minOrNull()!! }
        3 -> parseOperator(pos) { values -> values.maxOrNull()!! }
        5 -> parseOperator(pos) { values -> if (values[0] > values[1]) 1L else 0L }
        6 -> parseOperator(pos) { values -> if (values[0] < values[1]) 1L else 0L }
        7 -> parseOperator(pos) { values -> if (values[0] == values[1]) 1L else 0L }
        else -> throw IllegalStateException()
    }

    return state
}

private fun String.parseOperator(startPos: Int, block: (List<Long>) -> Long): Pair<Int, Long> {
    var pos = startPos
    val values = mutableListOf<Long>()
    when (this[pos++]) {
        '0' -> {
            val sizeInBits = substring(pos, pos + 15).toInt(2)
            pos += 15
            val endPos = pos + sizeInBits
            do {
                val (subPacketEnd, value) = parse(pos)
                pos = subPacketEnd
                values += value
            } while (pos < endPos)
        }
        '1' -> {
            val subPackets = substring(pos, pos + 11).toInt(2)
            pos += 11
            repeat(subPackets) {
                val (subPacketEnd, value) = parse(pos)
                pos = subPacketEnd
                values += value
            }
        }
    }
    return Pair(pos, block(values))
}