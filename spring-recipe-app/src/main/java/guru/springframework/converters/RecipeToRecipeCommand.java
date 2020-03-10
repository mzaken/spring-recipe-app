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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final NotesToNotesCommand notesToNotesCommand;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	
	public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand,
			NotesToNotesCommand notesToNotesCommand, IngredientToIngredientCommand ingredientToIngredientCommand) {
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.notesToNotesCommand = notesToNotesCommand;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		if (source == null ) {
			return null;
		}
		
		final RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setId(source.getId());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
		
		if (source.getNotes() != null) {
			recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
		}
		
		//Add Categories
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			source.getCategories().forEach(
					categoryCommand -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(categoryCommand)));
		}
		
		//Add Ingredients
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients().forEach(
					ingredientCommand -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredientCommand)));
		}
		
		return recipeCommand;
	}

}
