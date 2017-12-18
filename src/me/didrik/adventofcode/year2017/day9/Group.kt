package me.didrik.adventofcode.year2017.day9

class Group(private val stream: CharIterator, depth: Int) {
    private val groups = ArrayList<Group>()
    val cumulativeScore: Int
    var garbageCount = 0

    init {
        loop@
        while (stream.hasNext()) {
            val char = stream.nextChar()
            when (char) {
                '{' -> groups.add(Group(stream, depth+1))
                '<' -> readGarbage()
                '}' -> break@loop
            }
        }
        cumulativeScore = depth + groups.sumBy { group -> group.cumulativeScore }
        garbageCount += groups.sumBy { group -> group.garbageCount }
    }

    private fun readGarbage() {
        while (stream.hasNext()) {
            val char = stream.nextChar()
            when (char) {
                '!' -> stream.nextChar()
                '>' -> return
                else -> ++garbageCount
            }
        }
    }
}