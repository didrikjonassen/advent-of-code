package me.didrik.adventofcode.year2017.day8

import me.didrik.adventofcode.year2017.day8.Condition.*
import me.didrik.adventofcode.year2017.day8.Operation.DEC
import me.didrik.adventofcode.year2017.day8.Operation.INC
import java.io.File

class IHeardYouLikeRegistersHard {
    private val instructions = File("src/me/didrik/adventofcode/year2017/day8/IHeardYouLikeRegistersInput.txt").readLines().map { Instruction(it) }
    private val registers = HashMap<String, Int>()
    private var highestEverValue = 0

    fun doInstructions() {
        instructions
                .forEach {
                    instruction ->
                    if (!registers.containsKey(instruction.operationOn))
                        registers.put(instruction.operationOn, 0)
                    if (!registers.containsKey(instruction.conditionOn))
                        registers.put(instruction.conditionOn, 0)
                    if (getCondition(instruction.condition).invoke()(registers[instruction.conditionOn]!!, instruction.conditionValue))
                        registers[instruction.operationOn] = getOperation(instruction.operation).invoke()(registers[instruction.operationOn]!!, instruction.operationMagnitude)
                    if (registers[instruction.operationOn]!! > highestEverValue)
                        highestEverValue = registers[instruction.operationOn]!!
                }
    }

    fun printLargestValue() {
        println("highestEverValue = $highestEverValue")
    }

    private companion object {
        fun getCondition(condition: Condition) = {
            when (condition) {
                E -> {a: Int, b: Int -> a == b}
                NE -> {a: Int, b: Int -> a != b}
                LT -> {a: Int, b: Int -> a < b}
                GT -> {a: Int, b: Int -> a > b}
                LTE -> {a: Int, b: Int -> a <= b}
                GTE -> {a: Int, b: Int -> a >= b}
            }
        }

        fun getOperation(operation: Operation) = {
            when (operation) {
                DEC -> {a: Int, b: Int -> a - b }
                INC -> {a: Int, b: Int -> a + b }
            }
        }
    }
}

fun main(args: Array<String>) {
    val iHeardYouLikeRegistersHard = IHeardYouLikeRegistersHard()
    iHeardYouLikeRegistersHard.doInstructions()
    iHeardYouLikeRegistersHard.printLargestValue()
}

