package me.didrik.adventofcode.year2017.day8

class Instruction(string: String) {
    val operationOn: String
    val operation: Operation
    val operationMagnitude: Int
    val conditionOn: String
    val condition: Condition
    val conditionValue: Int

    init {
        val groupValues = regex.matchEntire(string)!!.groupValues
        operationOn = groupValues[1]
        operation = Operation.forValue(groupValues[2])
        operationMagnitude = Integer.parseInt(groupValues[3])
        conditionOn = groupValues[4]
        condition = Condition.forValue(groupValues[5])
        conditionValue = Integer.parseInt(groupValues[6])
    }

    private companion object {
        private val regex = Regex("""(\w+) (inc|dec) (-?\d+) if (\w+) ([<>=!]+) (-?\d+)""")
    }
}

enum class Condition(val value: String) {
    E("=="), NE("!="), LT("<"), GT(">"), LTE("<="), GTE(">=");

    companion object {
        private val values = Condition.values()

        fun forValue(s: String): Condition {
            return values.first { it.value == s }
        }
    }
}

enum class Operation(val value: String) {
    DEC("dec"), INC("inc");

    companion object {
        private val values = Operation.values()

        fun forValue(s: String): Operation {
            return values.first { it.value == s }
        }
    }
}
