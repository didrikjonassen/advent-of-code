package me.didrik.adventofcode.year2021.day17

import me.didrik.adventofcode.solve

fun main() {
    val xrange = 48..70
    val yrange = -189..-148
    var counter = 0L
    for (x in 10..70) {
        for (y in -189..188) {
            var xvelocity = x
            var yvelocity = y
            var tempx = x
            var tempy = y
            while (tempx <= 70 && tempy >= -189) {
                if (tempx in xrange && tempy in yrange) {
                    counter++
                    break
                }
                tempx += if (--xvelocity > 0) xvelocity else 0
                tempy += --yvelocity
            }
        }
    }
    solve(counter)
}