/**
 * 
 */
package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final CategoryCommandToCategory categoryCommandToCategory;
	private final NotesCommandToNotes notesCommandToNotes;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory,
			NotesCommandToNotes notesCommandToNotes, IngredientCommandToIngredient ingredientCommandToIngredient) {
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.notesCommandToNotes = notesCommandToNotes;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}
	
	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null ) {
			return null;
		}
		
		final Recipe recipe = new Recipe();
		recipe.setCookTime(source.getCookTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setId(source.getId());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		
		if (source.getNotes() != null) {
			recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
		}
		
		//Add Categories
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(
					categoryCommand -> recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
		}
		
		//Add Ingredients
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach(
					ingredientCommand -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)));
		}
		
		return recipe;
	}

}
