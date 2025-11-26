package com.sedefali.simplerecipesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sedefali.simplerecipesapp.data.model.Recipe
import com.sedefali.simplerecipesapp.databinding.ItemRecipeBinding

class RecipeAdapter(
    private val recipes: MutableList<Recipe>,
    private val listener: OnRecipeActionListener
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    interface OnRecipeActionListener {
        fun onEdit(recipe: Recipe, position: Int)
        fun onDelete(recipe: Recipe, position: Int)
    }

    inner class RecipeViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe, position: Int) {
            binding.tvRecipeTitle.text = recipe.title
            binding.tvRecipeDescription.text = recipe.description

            binding.btnEdit.setOnClickListener {
                listener.onEdit(recipe, position)
            }

            binding.btnDelete.setOnClickListener {
                listener.onDelete(recipe, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], position)
    }

    override fun getItemCount(): Int = recipes.size
}

