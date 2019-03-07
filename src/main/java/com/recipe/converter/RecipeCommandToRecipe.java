package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.RecipeCommand;
import com.recipe.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final NotesCommandToNotes notesCommandToNotes;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
			CategoryCommandToCategory categoryCommandToCategory,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.notesCommandToNotes = notesCommandToNotes;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand recipeCommand) {
		if (recipeCommand == null) {
			return null;
		}

		Recipe recipe = new Recipe();
		recipe.setId(recipeCommand.getId());
		recipe.setDescription(recipeCommand.getDescription());
		recipe.setPrepTime(recipeCommand.getPrepTime());
		recipe.setCookTime(recipeCommand.getCookTime());
		recipe.setServings(recipeCommand.getServings());
		recipe.setSource(recipeCommand.getSource());
		recipe.setUrl(recipeCommand.getUrl());
		recipe.setDirections(recipeCommand.getDirections());
		recipe.setImage(recipeCommand.getImage());
		recipe.setDifficulty(recipeCommand.getDifficulty());
		recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotesCommand()));
		if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
			recipeCommand.getCategories()
					.forEach(category -> recipe.getCategories().add(categoryCommandToCategory.convert(category)));
		}
		if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
			recipeCommand.getIngredients().forEach(
					ingredient -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredient)));
		}
		return recipe;
	}
	
}
