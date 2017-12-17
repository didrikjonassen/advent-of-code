package `1`

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/1/InverseCaptchaInput.txt")
    val input = file.readText()
    val integerArray = input.map { char -> Integer.parseInt(char.toString()) }
    val sum = (0 until input.length/2)
            .filter { i -> integerArray[i] == integerArray[i + input.length/2] }
            .sumBy { 2 * integerArray[it] }
    println("sum = $sum")
}