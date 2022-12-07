package me.didrik.adventofcode.year2022.day07

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2022/day07/Input.txt")
    val input = file.readLines()
    val root = Dir(null)
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
            currentDir.addDir(it.removePrefix("dir "), Dir(currentDir))
        } else {
            currentDir.addFile(it.split(" ")[0].toLong())
        }
    }
    val totalSize = computeTotalSize(root)
    solve(totalSize)

}

private fun computeTotalSize(dir: Dir): Long {
    return if (dir.size <= 100000) {
        dir.size + dir.dirs.values.sumOf { computeTotalSize(it) }
    } else {
        dir.dirs.values.sumOf { computeTotalSize(it) }
    }
}

private class Dir(val parent: Dir?) {
    val dirs = mutableMapOf<String, Dir>()
    var size = 0L

    fun addDir(name: String, dir: Dir) {
        dirs += name to dir
    }

    fun addFile(fileSize: Long) {
        size += fileSize
        parent?.addFile(fileSize)
    }
}