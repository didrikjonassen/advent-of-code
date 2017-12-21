package me.didrik.adventofcode.year2017.day17

fun main(args: Array<String>) {
    val input = 328
    val memory = ArrayList<Int>()
    memory.add(0)
    var currentPosition = 0
    val answer = (1 .. 2017)
            .map { i ->
                currentPosition += input
                currentPosition %= memory.size
                memory.add(++currentPosition, i)
                memory[(currentPosition + 1) % memory.size]
            }.last()
    println("answer = $answer")
}