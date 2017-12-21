package me.didrik.adventofcode.year2017.day18

import java.io.File

fun main(args: Array<String>) {
    val instructions = File("src/me/didrik/adventofcode/year2017/day18/DuetInput.txt").readLines()
            .map {
                val instruction = it.split(' ')
                if (instruction.size == 2) {
                    Triple(instruction[0], instruction[1], "")
                } else {
                    Triple(instruction[0], instruction[1], instruction[2])
                }
            }
    val registers = instructions
            .map { it.second }
            .filter { it[0].isLetter() }
            .distinct()
            .associate { Pair(it, 0L) }
            .toMutableMap()

    val recoverFrequency = compute(0, instructions, registers)
    println("recoverFrequency = $recoverFrequency")
}

tailrec fun compute(pointer: Int, instructions: List<Triple<String, String, String>>, registers: MutableMap<String, Long>, sound: Long = 0L): Long {
    val (instruction, register, value) = instructions[pointer]
    return when (instruction) {
        "snd" -> compute(pointer + 1, instructions, registers, valueOfRegister(registers, register))
        "set" -> {
            registers[register] = valueOfRegister(registers, value)
            compute(pointer + 1, instructions, registers, sound)
        }
        "add" -> {
            registers[register] = registers[register]!! + valueOfRegister(registers, value)
            compute(pointer + 1, instructions, registers, sound)
        }
        "mul" -> {
            registers[register] = registers[register]!! * valueOfRegister(registers, value)
            compute(pointer + 1, instructions, registers, sound)
        }
        "mod" -> {
            registers[register] = registers[register]!! % valueOfRegister(registers, value)
            compute(pointer + 1, instructions, registers, sound)
        }
        "rcv" ->
            if (valueOfRegister(registers, register) == 0L)
                compute(pointer + 1, instructions, registers, sound)
            else
                sound
        "jgz" ->
            if (valueOfRegister(registers, register) > 0)
                compute(pointer + valueOfRegister(registers, value).toInt(), instructions, registers, sound)
            else
                compute(pointer + 1, instructions, registers, sound)
        else -> {throw IllegalArgumentException("Something went wrong")}
    }
}

fun valueOfRegister(registers: Map<String, Long>, registerOrValue: String) =
        registers[registerOrValue] ?: registerOrValue.toLong()