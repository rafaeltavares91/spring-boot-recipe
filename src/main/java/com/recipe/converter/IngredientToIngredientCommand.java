package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.IngredientCommand;
import com.recipe.domain.Ingredient;

import lombok.Synchronized;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommandToUnitOfMeasure;

	public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureCommandToUnitOfMeasure) {
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient ingredient) {
		if (ingredient == null) {
			return null;
		}

		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ingredient.getId());
		ingredientCommand.setDescription(ingredient.getDescription());
		ingredientCommand.setAmount(ingredient.getAmount());
		ingredientCommand
				.setUnitOfMeasureCommand(unitOfMeasureCommandToUnitOfMeasure.convert(ingredient.getUnitOfMeasure()));
		return ingredientCommand;
	}
	
}
