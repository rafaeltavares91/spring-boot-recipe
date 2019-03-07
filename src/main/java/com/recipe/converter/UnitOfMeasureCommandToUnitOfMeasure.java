package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.UnitOfMeasureCommand;
import com.recipe.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
		if (unitOfMeasureCommand == null) {
			return null;
		}

		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(unitOfMeasureCommand.getId());
		uom.setDescription(unitOfMeasureCommand.getDescription());
		return uom;
	}
	
}
