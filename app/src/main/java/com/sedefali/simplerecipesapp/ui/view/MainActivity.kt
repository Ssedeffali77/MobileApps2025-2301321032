package com.sedefali.simplerecipesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.sedefali.simplerecipesapp.data.model.Recipe
import com.sedefali.simplerecipesapp.databinding.ActivityMainBinding
import com.sedefali.simplerecipesapp.ui.adapter.RecipeAdapter
import com.sedefali.simplerecipesapp.ui.view.AddRecipeActivity

class MainActivity : AppCompatActivity(), RecipeAdapter.OnRecipeActionListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipeList = mutableListOf<Recipe>()

    companion object {
        const val ADD_RECIPE_REQUEST_CODE = 100
        const val EDIT_RECIPE_REQUEST_CODE = 101
    }

    private var editingPosition: Int = -1 // пази позицията на рецептата при edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recipeAdapter = RecipeAdapter(recipeList, this)
        binding.recyclerViewRecipes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recipeAdapter
        }

        // Тестови данни
        recipeList.add(Recipe(title = "Pancakes", description = "Fluffy pancakes with syrup"))
        recipeList.add(Recipe(title = "Salad", description = "Fresh veggie salad with dressing"))
        recipeList.add(Recipe(title = "Spaghetti", description = "Classic spaghetti with tomato sauce"))
        recipeAdapter.notifyDataSetChanged()

        binding.fabAddRecipe.setOnClickListener {
            val intent = Intent(this, AddRecipeActivity::class.java)
            startActivityForResult(intent, ADD_RECIPE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != RESULT_OK || data == null) return

        val title = data.getStringExtra("recipe_title") ?: return
        val description = data.getStringExtra("recipe_description") ?: return

        if (requestCode == ADD_RECIPE_REQUEST_CODE) {
            val newRecipe = Recipe(title = title, description = description)
            addRecipe(newRecipe)
        } else if (requestCode == EDIT_RECIPE_REQUEST_CODE && editingPosition >= 0) {
            val editedRecipe = recipeList[editingPosition].copy(title = title, description = description)
            recipeList[editingPosition] = editedRecipe
            recipeAdapter.notifyItemChanged(editingPosition)
            editingPosition = -1
            Toast.makeText(this, "Recipe updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addRecipe(recipe: Recipe) {
        recipeList.add(recipe)
        recipeAdapter.notifyItemInserted(recipeList.size - 1)
    }

    // --- RecipeAdapter.OnRecipeActionListener ---
    override fun onEdit(recipe: Recipe, position: Int) {
        editingPosition = position
        val intent = Intent(this, AddRecipeActivity::class.java).apply {
            putExtra("recipe_title", recipe.title)
            putExtra("recipe_description", recipe.description)
        }
        startActivityForResult(intent, EDIT_RECIPE_REQUEST_CODE)
    }

    override fun onDelete(recipe: Recipe, position: Int) {
        recipeList.removeAt(position)
        recipeAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "Deleted: ${recipe.title}", Toast.LENGTH_SHORT).show()
    }
}


