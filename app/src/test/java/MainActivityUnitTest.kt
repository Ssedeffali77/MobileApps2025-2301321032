package com.sedefali.simplerecipesapp

import com.sedefali.simplerecipesapp.data.model.Recipe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainActivityUnitTest {

    private lateinit var recipeList: MutableList<Recipe>

    @Before
    fun setUp() {
        recipeList = mutableListOf(
            Recipe(title = "Pancakes", description = "Fluffy pancakes"),
            Recipe(title = "Salad", description = "Fresh salad")
        )
    }

    @Test
    fun addRecipe_increasesListSize() {
        val newRecipe = Recipe(title = "Spaghetti", description = "Classic spaghetti")
        recipeList.add(newRecipe)
        assertEquals(3, recipeList.size)
        assertTrue(recipeList.contains(newRecipe))
    }

    @Test
    fun deleteRecipe_decreasesListSize() {
        val recipeToDelete = recipeList[0]
        recipeList.removeAt(0)
        assertEquals(1, recipeList.size)
        assertFalse(recipeList.contains(recipeToDelete))
    }

    @Test
    fun editRecipe_updatesTitleAndDescription() {
        val recipe = recipeList[0]
        recipeList[0] = recipe.copy(title = "New Pancakes", description = "Updated description")
        assertEquals("New Pancakes", recipeList[0].title)
        assertEquals("Updated description", recipeList[0].description)
    }
}
