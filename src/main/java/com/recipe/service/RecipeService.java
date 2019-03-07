package com.recipe.service;

import java.util.Set;

import com.recipe.command.RecipeCommand;
import com.recipe.domain.Recipe;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long id);

    RecipeCommand getRecipeCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    void deleteRecipeById(Long id);

}
