package me.didrik.adventofcode.year2017.day12

import java.io.File

fun main(args: Array<String>) {
    val vertices = File("src/me/didrik/adventofcode/year2017/day12/DigitalPlumberInput.txt").readLines()
    val edges = vertices.map {
        val (_, edges) = it.split("<->")
        edges.trim().split(", ").map { Integer.parseInt(it) }
    }

    val coloured = IntArray(vertices.size)
    var group = 1
    while (0 in coloured) {
        val vertex = coloured.indexOf(0)
        traverse(vertex, edges, coloured, group++)
    }
    val groups = coloured.distinct().count()
    println("groups = $groups")
}

fun traverse(vertex: Int, edges: List<List<Int>>, coloured: IntArray, group: Int) {
    if (coloured[vertex] != 0) return
    coloured[vertex] = group
    edges[vertex].forEach { edge ->
        traverse(edge, edges, coloured, group)
    }
}