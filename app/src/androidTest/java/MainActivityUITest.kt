package com.sedefali.simplerecipesapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sedefali.simplerecipesapp.ui.view.AddRecipeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun addRecipe_displayedInRecyclerView() {
        // Клик върху FAB за добавяне на рецепта
        onView(withId(R.id.fabAddRecipe)).perform(click())

        // Въвеждане на заглавие и описание
        onView(withId(R.id.etRecipeTitle)).perform(typeText("Test Recipe"), closeSoftKeyboard())
        onView(withId(R.id.etRecipeDescription)).perform(typeText("This is a test recipe"), closeSoftKeyboard())

        // Клик върху бутона Save
        onView(withId(R.id.btnSaveRecipe)).perform(click())

        // Проверка дали новата рецепта се показва в RecyclerView
        onView(withText("Test Recipe")).check(matches(isDisplayed()))
        onView(withText("This is a test recipe")).check(matches(isDisplayed()))
    }

    @Test
    fun backFromAddRecipe_returnsToMainActivity() {
        // Клик върху FAB за добавяне на рецепта
        onView(withId(R.id.fabAddRecipe)).perform(click())

        // Натискане на Back
        pressBack()

        // Проверка дали RecyclerView е видим
        onView(withId(R.id.recyclerViewRecipes)).check(matches(isDisplayed()))
    }
}
