package me.didrik.adventofcode.year2020.day20

import me.didrik.adventofcode.solve
import java.io.File

private typealias Tile = List<String>

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day20/JurassicJigsawInput.txt")
    val input = file.readLines()

    val tilePositions = input
        .mapIndexed { index, row ->
            Pair(index, row)
        }.filter { it.second.startsWith("Tile") }
        .map { it.first }

    val tiles = tilePositions
        .map {
            Pair(
                input[it].substringAfter(" ").substringBefore(":").toLong(),
                input.subList(it + 1, it + 11)
            )
        }.associateBy(Pair<Long, Tile>::first, Pair<Long, Tile>::second)
        .toMutableMap()

    val edgeMap = mutableMapOf<String, Long>()

    for (tile in tiles) {
        tile.putAllEdgesIn(edgeMap)
    }

    val corners = edgeMap.values
        .asSequence()
        .filter { it < 10_000 }
        .groupBy { it }
        .filter { it.value.size == 2 }
        .map { it.key }
        .toMutableList()

    val topleft = corners.first()
    var topleftTile = tiles[topleft]!!
    edgeMap.removeEdges(topleftTile, topleft)
    while (!(edgeMap[topleftTile.top()]!! == 1L && edgeMap[topleftTile.left()]!! == 1L)) {
        topleftTile = topleftTile.rotate()
    }

    val jiggsaw = mutableListOf(mutableListOf(topleftTile))
    do { // Top row
        val previousTile = jiggsaw[0].last()
        val tileId = edgeMap[previousTile.right()]!!
        var tile = tiles[tileId]!!
        edgeMap.removeEdges(tile, tileId)
        while (tile.left() != previousTile.right()) {
            tile = tile.rotate()
        }
        if (edgeMap[tile.top()] != 1L) {
            tile = tile.flip()
        }
        jiggsaw[0].add(tile)
    } while (tileId !in corners)

    do { // Left collumn
        val previousTile = jiggsaw.last().first()
        val tileId = edgeMap[previousTile.bottom()]!!
        var tile = tiles[tileId]!!
        edgeMap.removeEdges(tile, tileId)
        while (tile.top() != previousTile.bottom()) {
            tile = tile.rotate()
        }
        if (edgeMap[tile.left()] != 1L) {
            tile = tile.mirror()
        }
        jiggsaw.add(mutableListOf(tile))
    } while (tileId !in corners)

    // The rest
    for (i in 1 until jiggsaw.size) {
        for (k in 1 until jiggsaw[0].size) {
            val previousTile = jiggsaw[i][k - 1]
            val tileId = edgeMap[previousTile.right()]!!
            var tile = tiles[tileId]!!
            edgeMap.removeEdges(tile, tileId)
            while (tile.left() != previousTile.right()) {
                tile = tile.rotate()
            }
            if (edgeMap[tile.top()] != 1L) {
                tile = tile.flip()
            }
            jiggsaw[i].add(tile)
        }
    }

    var joinedJiggsaw = jiggsaw
        .map { it.joinCollumns() }
        .joinRows()

    var dragons = joinedJiggsaw.countDragons()
    repeat(3) {
        joinedJiggsaw = joinedJiggsaw.rotate()
        dragons += joinedJiggsaw.countDragons()
    }
    joinedJiggsaw = joinedJiggsaw.flip()
    dragons += joinedJiggsaw.countDragons()
    repeat(3) {
        joinedJiggsaw = joinedJiggsaw.rotate()
        dragons += joinedJiggsaw.countDragons()
    }

    val roughness = joinedJiggsaw
        .map { it.filter { it == '#' } }
        .map { it.length }
        .sum() - dragons * dragon.map { it.pattern.filter { it == '#' }.length }.sum()

    solve(roughness)
}

private fun Map.Entry<Long, Tile>.putAllEdgesIn(map: MutableMap<String, Long>) {
    map[value.top()] = key * (map[value.top()] ?: 1L)
    map[value.bottom()] = key * (map[value.bottom()] ?: 1L)
    map[value.left()] = key * (map[value.left()] ?: 1L)
    map[value.right()] = key * (map[value.right()] ?: 1L)
}

private fun MutableMap<String, Long>.removeEdges(tile: Tile, id: Long) {
    this[tile.top()] = this[tile.top()]!! / id
    this[tile.bottom()] = this[tile.bottom()]!! / id
    this[tile.left()] = this[tile.left()]!! / id
    this[tile.right()] = this[tile.right()]!! / id
}

private fun String.toCanonicalEdge(): String {
    return listOf(this, this.reversed()).sorted().joinToString(separator = "")
}

private fun Tile.top() = this[0].toCanonicalEdge()
private fun Tile.bottom() = this[9].toCanonicalEdge()
private fun Tile.left() = this.map { it[0] }.joinToString(separator = "").toCanonicalEdge()
private fun Tile.right() = this.map { it[9] }.joinToString(separator = "").toCanonicalEdge()

private fun Tile.rotate(): Tile =
    (size - 1 downTo 0)
        .map { index -> this.map { it[index] }.joinToString(separator = "") }

private fun Tile.flip(): Tile =
    reversed()

private fun Tile.mirror(): Tile =
    map { it.reversed() }

private fun List<Tile>.joinCollumns(): Tile =
    (this[0].indices).map { row ->
        this.joinToString(separator = "") { it[row].substring(1, 9) }
    }

private fun List<Tile>.joinRows(): Tile =
    flatMap { it.subList(1, 9) }

private val dragon =
    """
                  # 
#    ##    ##    ###
 #  #  #  #  #  #   
    """.trimIndent().replace(" ", ".").lines().map { it.toRegex() }

private fun Tile.countDragons(): Int {
    var count = 0
    for (i in 0 until size - dragon.size) {
        search@
        for (k in 0 until this[0].length - dragon[0].pattern.length) {
            for (row in 0 until 3) {
                if (!this[i + row].substring(k, k + dragon[0].pattern.length).matches(dragon[row])) {
                    continue@search
                }
            }
            count++
        }
    }
    return count
}