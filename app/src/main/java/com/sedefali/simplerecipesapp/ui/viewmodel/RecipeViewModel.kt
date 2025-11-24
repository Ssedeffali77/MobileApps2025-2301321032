package com.sedefali.simplerecipesapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedefali.simplerecipesapp.data.model.Recipe
import com.sedefali.simplerecipesapp.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    fun loadRecipes() {
        viewModelScope.launch {
            repository.allRecipes.collect { list ->
                _recipes.value = list
            }
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insert(recipe)
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.update(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.delete(recipe)
        }
    }
}
