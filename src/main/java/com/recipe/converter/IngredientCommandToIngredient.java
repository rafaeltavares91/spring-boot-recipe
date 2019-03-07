package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.IngredientCommand;
import com.recipe.domain.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand ingredientCommand) {
		if (ingredientCommand == null) {
			return null;
		}

		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientCommand.getId());
		ingredient.setDescription(ingredientCommand.getDescription());
		ingredient.setAmount(ingredientCommand.getAmount());
		ingredient.setUnitOfMeasure(
				unitOfMeasureCommandToUnitOfMeasure.convert(ingredientCommand.getUnitOfMeasureCommand()));
		return ingredient;
	}
	
}
