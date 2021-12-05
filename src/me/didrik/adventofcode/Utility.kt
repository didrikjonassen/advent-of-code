package me.didrik.adventofcode

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

fun solve(answer: Any?) {
    println(answer.toString())
    val selection = StringSelection(answer.toString())
    Toolkit.getDefaultToolkit().systemClipboard.setContents(selection, selection)
}