package day1

import java.io.File

fun main(args: Array<String>) {
    val file = File("src/day1/InverseCaptchaInput.txt")
    val input = file.readText()
    val extendedInput = input + input.toCharArray()[0]
    val integerArray = extendedInput.map { char -> Integer.parseInt(char.toString()) }
    val sum = (0 until input.length)
            .filter { integerArray[it] == integerArray[it+1] }
            .sumBy { integerArray[it] }
    println("sum = $sum")
}
