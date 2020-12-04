package me.didrik.adventofcode.year2020.day04

import java.io.File

val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day04/PassportProcessingInput.txt")
    val input = file.readLines()
    val passports = mutableListOf<Map<String, String>>()
    var currentPassport = mutableMapOf<String, String>()
    for (line in input) {
        if (line.isEmpty()) {
            passports += currentPassport
            currentPassport = mutableMapOf()
        } else {
            line.split(" ")
                    .map { it.split(":") }
                    .forEach { currentPassport[it[0]] = it[1] }
        }
    }
    passports += currentPassport

    val validPassports = passports.filter { passport ->
        passport.keys.containsAll(requiredFields)
    }.count()

    println("validPassports = $validPassports")
}