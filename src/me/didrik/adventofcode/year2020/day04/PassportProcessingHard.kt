package me.didrik.adventofcode.year2020.day04

import java.io.File

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
        passport.isValid()
    }.count()

    println("validPassports = $validPassports")
}

private fun Map<String, String>.isValid(): Boolean {
    val byr = get("byr")?.toIntOrNull() ?: return false
    if (byr < 1920 || byr > 2002) {
        return false
    }

    val iyr = get("iyr")?.toIntOrNull() ?: return false
    if (iyr < 2010 || iyr > 2020) {
        return false
    }

    val eyr = get("eyr")?.toIntOrNull() ?: return false
    if (eyr < 2020 || eyr > 2030) {
        return false
    }

    val (hgt, type) = "(\\d+)(in|cm)".toRegex().matchEntire(get("hgt") ?: return false)?.destructured ?: return false
    if (type == "in" && (hgt.toInt() < 59 || hgt.toInt() > 76)) {
        return false
    }
    if (type == "cm" && (hgt.toInt() < 150 || hgt.toInt() > 193)) {
        return false
    }

    get("hcl")?.takeIf {
        it.matches("#[0-9a-f]{6}".toRegex())
    } ?: return false

    if (get("ecl") !in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")) {
        return false
    }

    return get("pid")?.matches("\\d{9}".toRegex()) ?: false
}