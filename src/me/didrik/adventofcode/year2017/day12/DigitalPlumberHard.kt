package me.didrik.adventofcode.year2017.day12

import java.io.File

fun main(args: Array<String>) {
    val vertices = File("src/me/didrik/adventofcode/year2017/day12/DigitalPlumberInput.txt").readLines()
    val edges = vertices.map {
        val (_, edges) = it.split("<->")
        edges.trim().split(", ").map { Integer.parseInt(it) }
    }

    val coloured = BooleanArray(vertices.size)
    traverse(0, edges, coloured)
    val connectedVertices = coloured.filter { it }.count()
    println("connectedVertices = $connectedVertices")
}

fun traverse(vertice: Int, edges: List<List<Int>>, coloured: BooleanArray) {
    if (coloured[vertice]) return
    coloured[vertice] = true
    edges[vertice].forEach {edge ->
        traverse(edge, edges, coloured)
    }
}