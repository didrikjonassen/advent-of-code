package me.didrik.adventofcode.year2021.day16

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2021/day16/Input.txt")
    val input = file.readLines()
    val bits = input.first().map { it.toString().toInt(16).toString(2) }.joinToString("") { "0000".substring(0, 4 - it.length) + it }

    var versionSum = 0

    fun parse(startPos: Int): Int {
        var pos = startPos + 3
        val version = bits.substring(startPos, pos).toInt(2)
        versionSum += version
        val type = bits.substring(pos, pos + 3).toInt(2)
        pos += 3
        when (type) {
            4 -> {
                do {
                    val groupPrefix = bits[pos++]
                    pos += 4
                } while (groupPrefix == '1')
            }
            else -> {
                when (bits[pos++]) {
                    '0' -> {
                        val sizeInBits = bits.substring(pos, pos + 15).toInt(2)
                        pos += 15
                        val endPos = pos + sizeInBits
                        do {
                            pos = parse(pos)
                        } while (pos < endPos)
                    }
                    '1' -> {
                        val subPackets = bits.substring(pos, pos + 11).toInt(2)
                        pos += 11
                        repeat(subPackets) {
                            pos = parse(pos)
                        }
                    }
                }
            }
        }

        return pos
    }

    parse(0)
    solve(versionSum)
}