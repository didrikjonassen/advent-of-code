package me.didrik.adventofcode.year2018.day05

import java.io.File
import java.util.*
import kotlin.experimental.xor

fun main() {
    val file = File("src/me/didrik/adventofcode/year2018/day05/AlchemicalReductionInput.txt")
    val input = file.readBytes()
    val output = ArrayDeque<Byte>()

    for (byte in input) {
        when {
            output.isEmpty() -> output.push(byte)
            byte.sameCharDifferentCase(output.peek()) -> output.pop()
            else -> output.push(byte)
        }
    }

    println("Remaining units = ${output.size}")

}

private fun Byte.sameCharDifferentCase(other: Byte) =
        this xor other == 0x20.toByte()