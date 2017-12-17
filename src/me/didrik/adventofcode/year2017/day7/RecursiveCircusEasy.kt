package me.didrik.adventofcode.year2017.day7

import java.io.File

fun main(args: Array<String>) {
    val memory = File("src/me/didrik/adventofcode/year2017/day7/RecursiveCircusInput.txt").readLines().map { Program(it) }
    val children = memory.flatMap { program -> program.subTowerNames }
    val root = memory.filter { program -> !children.contains(program.name) }[0]
    println("root = ${root.name}")
}
