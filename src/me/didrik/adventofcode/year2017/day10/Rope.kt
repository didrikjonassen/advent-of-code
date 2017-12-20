package me.didrik.adventofcode.year2017.day10

class Rope(private val length: Int) : Iterable<Int> {
    private val list = (0 until length).toList().toIntArray()

    operator fun set(pos: Int, value: Int) {
        list[pos % length] = value
    }

    operator fun get(pos: Int) = list[pos % length]

    override fun iterator(): Iterator<Int> {
        return list.iterator()
    }
}