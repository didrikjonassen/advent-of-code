package me.didrik.adventofcode.year2017.day16

import java.util.Collections.swap

class Line {
    var programs = "abcdefghijklmnop".toMutableList()

    fun move(move: String) {
        when (move[0]) {
            's' -> {
                val length = Integer.parseInt(move.substring(1))
                programs = (programs.subList(programs.size - length, programs.size) + programs.subList(0, programs.size - length)).toMutableList()
            }
            'x' -> {
                val (first, second) = move.substring(1).split('/').map { Integer.parseInt(it) }
                swap(programs, first, second)
            }
            'p' -> {
                val first = programs.indexOf(move[1])
                val second = programs.indexOf(move[3])
                move("x$first/$second")
            }
        }
    }
}