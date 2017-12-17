package me.didrik.adventofcode.year2017.day7

class Program(string: String) {
    val name: String
    val weight: Int
    val subTowerNames: List<String>
    var subTowers: List<Program> = ArrayList()
    var towerWeight: Int = 0
    init {
        val matched = regex.matchEntire(string)
        name = matched!!.groupValues[1]
        weight = Integer.parseInt(matched.groupValues[2])
        subTowerNames = matched.groupValues[4].split(", ").filter { it.isNotEmpty() }
    }

    private companion object {
        private val regex = Regex("""([a-z]+) \((\d+)\)( -> (([a-z]+(, )?)*))?""")
    }
}