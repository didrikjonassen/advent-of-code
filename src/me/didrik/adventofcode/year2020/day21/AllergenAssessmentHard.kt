package me.didrik.adventofcode.year2020.day21

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day21/AllergenAssessmentInput.txt")
    val input = file.readLines()

    val possibleIngredients = mutableMapOf<String, MutableSet<String>>()
    val allIngredients = mutableSetOf<String>()

    for (line in input) {
        val ingredients = line.substringBefore(" (").split(" ")
        val allergens = line.substringAfter("(contains ").substringBefore(")").split(", ")
        allIngredients += ingredients

        for (allergen in allergens) {
            if (allergen !in possibleIngredients) {
                possibleIngredients[allergen] = ingredients.toSet().toMutableSet()
            } else {
                possibleIngredients[allergen] = possibleIngredients[allergen]!!.intersect(ingredients.toSet()).toMutableSet()
            }
        }
    }

    val usedIngredients = mutableSetOf<String>()
    val allergenToIngredient = mutableListOf<Pair<String, String>>()

    while (usedIngredients.size < possibleIngredients.size) {
        val solvedIngredient = possibleIngredients.filterValues { it.size == 1 }
        solvedIngredient.forEach {
            usedIngredients += it.value.first()
            allergenToIngredient += Pair(it.key, it.value.first())
        }
        possibleIngredients.values.forEach{
            it -= usedIngredients
        }
    }

    val answer = allergenToIngredient
            .sortedBy { it.first }
            .joinToString(separator = ",") { it.second }
    solve(answer)
}