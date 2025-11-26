package com.sedefali.simplerecipesapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sedefali.simplerecipesapp.data.model.Recipe
import com.sedefali.simplerecipesapp.databinding.FragmentAddRecipeBinding

class AddRecipeFragment : Fragment() {

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveRecipe.setOnClickListener {
            val title = binding.etRecipeTitle.text.toString().trim()
            val description = binding.etRecipeDescription.text.toString().trim()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter title and description", Toast.LENGTH_SHORT).show()
            } else {
                val newRecipe = Recipe(title = title, description = description)
                Toast.makeText(requireContext(), "Recipe saved: ${newRecipe.title}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
