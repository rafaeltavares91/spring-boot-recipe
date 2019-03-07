package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.CategoryCommand;
import com.recipe.domain.Category;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category category) {
		if (category == null) {
			return null;
		}

		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(category.getId());
		categoryCommand.setDescription(category.getDescription());
		return categoryCommand;
	}
	
}
