package me.didrik.adventofcode.year2018.day05

import java.io.File
import java.util.*
import kotlin.experimental.or
import kotlin.experimental.xor

fun main() {
    val file = File("src/me/didrik/adventofcode/year2018/day05/AlchemicalReductionInput.txt")
    val input = file.readBytes()

    val shortestPolymerSize = ('a'..'z')
            .map { char ->
                input.filterNot { byte ->
                    byte.equalsIgnoreCase(char.toByte())
                }
            }.map { it.fullyReact() }
            .map { it.size }
            .min()!!

    println("Remaining units = $shortestPolymerSize")

}

private fun Byte.sameCharDifferentCase(other: Byte) =
        this xor other == 0x20.toByte()

private fun Byte.equalsIgnoreCase(other: Byte) =
        this xor other or 0x20 == 0x20.toByte()

private fun List<Byte>.fullyReact() =
        ArrayDeque<Byte>().also {
            for (byte in this) {
                when {
                    it.isEmpty() -> it.push(byte)
                    byte.sameCharDifferentCase(it.peek()) -> it.pop()
                    else -> it.push(byte)
                }
            }
        }