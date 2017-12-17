package me.didrik.adventofcode.year2017.day7

import java.io.File

fun main(args: Array<String>) {
    val memory = File("src/me/didrik/adventofcode/year2017/day7/RecursiveCircusInput.txt").readLines().map { Program(it) }.associateBy { program -> program.name }
    val children = memory.values.flatMap { program -> program.subTowerNames }
    val root = memory.values.filter { program -> !children.contains(program.name) }[0]
    memory.values.forEach {
        program ->
        program.subTowers = program.subTowerNames.map { subTowername -> memory[subTowername]!! }
    }
    calculateTowerWeights(root, memory)
    println("getCorrectWeight(root) = ${getCorrectWeight(root)}")
}

fun calculateTowerWeights(program: Program, memory: Map<String, Program>): Int {
    program.towerWeight = program.weight
    program.towerWeight += program.subTowers.map { calculateTowerWeights(it, memory) }.sum()
    return program.towerWeight
}

fun getCorrectWeight(program: Program): Int {

    fun getCorrectWeight(program: Program, delta: Int): Int {
        if (program.subTowers.distinctBy { tower -> tower.towerWeight }.count() == 1)
            return program.weight - delta
        return if (delta > 0)
            getCorrectWeight(program.subTowers.maxBy { tower -> tower.towerWeight }!!, delta)
        else
            getCorrectWeight(program.subTowers.minBy { tower -> tower.towerWeight }!!, delta)
    }

    val max = program.subTowers
    .map { tower -> tower.towerWeight }
    .max()
    val min = program.subTowers
            .map { tower -> tower.towerWeight }
            .min()
    return if (program.subTowers.map { it.towerWeight }.filter { it == max }.count() == 1)
        getCorrectWeight(program, max!! - min!!)
    else
        getCorrectWeight(program, min!! - max!!)

}
