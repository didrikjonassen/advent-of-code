package me.didrik.adventofcode.year2022.day13

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day13/Input.txt")
    val input = file.readLines()

    val result = input.windowed(2, 3) {
        val (left, right) = it.map { convert(it) }
        compareList(left, right)
    }.mapIndexed { index, b ->
        if (b!!) index + 1 else 0
    }.sum()
    solve(result)
}

private fun compareList(left: Element, right: Element): Boolean? {
    when {
        left is Node && right is Node -> {
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
        left is Leaf && right is Leaf -> {
            return if (left.int == right.int) {
                null
            } else {
                left.int < right.int
            }
        }
        left is Node && right is Leaf -> {
            return compareList(left, Node(mutableListOf(right)))
        }
        left is Leaf && right is Node -> {
            return compareList(Node(mutableListOf(left)), right)
        }
    }
    throw IllegalStateException()
}

private sealed class Element

private data class Node(
    val elements: MutableList<Element> = mutableListOf()
) : Element()

private data class Leaf(
    val int: Int
) : Element()

private fun convert(string: String): Element {
    if (string[0] != '[') {
        return Leaf(string.toInt())
    }

    var depth = 0
    val sb = StringBuilder()
    val node = Node()
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