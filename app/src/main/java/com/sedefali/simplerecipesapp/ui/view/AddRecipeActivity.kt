package com.sedefali.simplerecipesapp.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sedefali.simplerecipesapp.databinding.ActivityAddRecipeBinding

class AddRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecipeBinding
    private var recipePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Проверка дали това е Edit
        val titleFromIntent = intent.getStringExtra("recipe_title")
        val descriptionFromIntent = intent.getStringExtra("recipe_description")
        recipePosition = intent.getIntExtra("recipe_position", -1)

        if (titleFromIntent != null) binding.etRecipeTitle.setText(titleFromIntent)
        if (descriptionFromIntent != null) binding.etRecipeDescription.setText(descriptionFromIntent)

        binding.btnSaveRecipe.setOnClickListener {
            val title = binding.etRecipeTitle.text.toString().trim()
            val description = binding.etRecipeDescription.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please enter title and description", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent().apply {
                    putExtra("recipe_title", title)
                    putExtra("recipe_description", description)
                    if (recipePosition != -1) putExtra("recipe_position", recipePosition)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
