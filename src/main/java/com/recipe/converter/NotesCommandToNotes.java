package com.recipe.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.recipe.command.NotesCommand;
import com.recipe.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand notesCommand) {
		if (notesCommand == null) {
			return null;
		}

		Notes notes = new Notes();
		notes.setId(notesCommand.getId());
		notes.setRecipeNotes(notesCommand.getRecipeNotes());
		return notes;
	}
	
}
