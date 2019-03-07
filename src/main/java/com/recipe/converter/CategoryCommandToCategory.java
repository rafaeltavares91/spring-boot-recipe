package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.CategoryCommand;
import com.recipe.domain.Category;

import lombok.Synchronized;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand categoryCommand) {
		if (categoryCommand == null) {
			return null;
		}

		Category category = new Category();
		category.setId(categoryCommand.getId());
		category.setDescription(categoryCommand.getDescription());
		return category;
	}
	
}
