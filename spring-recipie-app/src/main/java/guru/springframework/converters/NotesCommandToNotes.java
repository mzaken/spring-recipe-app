/**
 * 
 */
package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import lombok.Synchronized;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

	private RecipeCommandToRecipe recipeCommandToRecipe;
	
	public NotesCommandToNotes(RecipeCommandToRecipe recipeCommandToRecipe) {
		this.recipeCommandToRecipe = recipeCommandToRecipe;
	}
	
	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if (source == null) {
			return null;
		}
		
		Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setNotes(source.getNotes());
		notes.setRecipe(recipeCommandToRecipe.convert(source.getRecipe()));
		
		return notes;
	}
}