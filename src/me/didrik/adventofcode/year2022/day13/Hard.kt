package me.didrik.adventofcode.year2022.day13

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day13/Input.txt")
    val input = file.readLines()

    val sorted = (input + listOf("[[2]]", "[[6]]"))
        .filter { it.isNotBlank() }
        .map { convert(it) }
        .sortedWith(Comparator { left, right ->
            when (compareList(left, right)) {
                true -> -1
                false -> 1
                else -> 0
            }
        })
        .map { it.toString() }
    solve((sorted.indexOf("[[2]]") + 1) * (sorted.indexOf("[[6]]") + 1))
}

private fun compareList(left: Element2, right: Element2): Boolean? {
    when {
        left is Node2 && right is Node2 -> {
            left.elements.indices.forEach { i ->
                if (i !in right.elements.indices) {
                    return false
                }
                val result = compareList(left.elements[i], right.elements[i])
                if (result != null) {
                    return result
                }
            }
            return if (left.elements.size < right.elements.size) {
                true
            } else {
                null
            }
        }
        left is Leaf2 && right is Leaf2 -> {
            return if (left.int == right.int) {
                null
            } else {
                left.int < right.int
            }
        }
        left is Node2 && right is Leaf2 -> {
            return compareList(left, Node2(mutableListOf(right)))
        }
        left is Leaf2 && right is Node2 -> {
            return compareList(Node2(mutableListOf(left)), right)
        }
    }
    throw IllegalStateException()
}

private sealed class Element2

private class Node2(
    val elements: MutableList<Element2> = mutableListOf()
) : Element2() {
    override fun toString(): String {
        return elements.toString()
    }
}

private class Leaf2(
    val int: Int
) : Element2() {
    override fun toString(): String {
        return int.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Leaf2

        if (int != other.int) return false

        return true
    }

    override fun hashCode(): Int {
        return int
    }


}

private fun convert(string: String): Element2 {
    if (string[0] != '[') {
        return Leaf2(string.toInt())
    }

    var depth = 0
    val sb = StringBuilder()
    val node = Node2()
    (1 until string.length - 1).forEach { i ->
        when (string[i]) {
            ',' -> {
                if (depth == 0) {
                    node.elements += convert(sb.toString())
                    sb.clear()
                } else {
                    sb.append(string[i])
                }
            }
            '[' -> {
                ++depth
                sb.append('[')
            }

            ']' -> {
                --depth
                sb.append(']')
            }
            else -> {
                sb.append(string[i])
            }
        }
    }
    if (sb.isNotEmpty()) {
        node.elements += convert(sb.toString())
    }
    return node
}