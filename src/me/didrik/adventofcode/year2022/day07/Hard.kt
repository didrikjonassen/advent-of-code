package me.didrik.adventofcode.year2022.day07

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day07/Input.txt")
    val input = file.readLines()
    val root = Dir2(null)
    var currentDir = root
    input.forEach {
        if (it == "$ cd /") {
            currentDir = root
        } else if (it == "$ cd ..") {
            currentDir = currentDir.parent!!
        } else if (it == "$ ls") {

        } else if (it.startsWith("$ cd")) {
            currentDir = currentDir.dirs[it.removePrefix("$ cd ")]!!
        } else if (it.startsWith("dir")) {
            currentDir.addDir(it.removePrefix("dir "), Dir2(currentDir))
        } else {
            currentDir.addFile(it.split(" ")[0].toLong())
        }
    }
    val capacity = 70000000L
    val requiredSpace = 30000000L
    val currentlyUnused = capacity - root.size
    val needToDelete = requiredSpace - currentlyUnused
    val minDirSizeToDelete = computeMinDirSizeToDelete(root, needToDelete)
    solve(minDirSizeToDelete)

}

private fun computeMinDirSizeToDelete(dir: Dir2, requiredSize: Long): Long? {
    return if (dir.size < requiredSize) {
        null
    } else {
        dir.dirs.values.mapNotNull { computeMinDirSizeToDelete(it, requiredSize) }.minOrNull() ?: dir.size
    }
}

private class Dir2(val parent: Dir2?) {
    val dirs = mutableMapOf<String, Dir2>()
    var size = 0L

    fun addDir(name: String, dir: Dir2) {
        dirs += name to dir
    }

    fun addFile(fileSize: Long) {
        size += fileSize
        parent?.addFile(fileSize)
    }
}