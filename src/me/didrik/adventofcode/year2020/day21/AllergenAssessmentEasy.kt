package me.didrik.adventofcode.year2020.day21

import me.didrik.adventofcode.solve
import java.io.File

fun main() {
    val file = File("src/me/didrik/adventofcode/year2020/day21/AllergenAssessmentInput.txt")
    val input = file.readLines()

    val possibleIngredients = mutableMapOf<String, Set<String>>()
    val allIngredients = mutableSetOf<String>()

    for (line in input) {
        val ingredients = line.substringBefore(" (").split(" ")
        val allergens = line.substringAfter("(contains ").substringBefore(")").split(", ")
        allIngredients += ingredients

        for (allergen in allergens) {
            if (allergen !in possibleIngredients) {
                possibleIngredients[allergen] = ingredients.toSet()
            } else {
                possibleIngredients[allergen] = possibleIngredients[allergen]!!.intersect(ingredients.toSet())
            }
        }
    }
    val safeIngredients = allIngredients - possibleIngredients.values.reduce{ acc, set -> acc.union(set) }
    val answer = input
            .flatMap { it.substringBefore(" (").split(" ") }
            .filter { it in safeIngredients }
            .count()

    solve(answer)
}