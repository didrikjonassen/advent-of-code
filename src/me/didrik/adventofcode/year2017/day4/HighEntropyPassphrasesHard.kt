package me.didrik.adventofcode.year2017.day4

import java.io.File

fun main(args: Array<String>) {
    val passphrases = File("src/me/didrik/adventofcode/year2017/day4/HighEntropyPassphraseInput.txt").readLines()
    val listsOfWords = passphrases.map { it.split(' ') }
    val setsOfWords = listsOfWords.map { HashSet<String>(it) }
    val count = listsOfWords.zip(setsOfWords).filter { (list, set) -> list.size == set.size }.count()
    println("count = $count")
}