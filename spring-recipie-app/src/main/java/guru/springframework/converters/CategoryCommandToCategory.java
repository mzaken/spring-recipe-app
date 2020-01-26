/**
 * 
 */
package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{
	
	private RecipeCommandToRecipe recipeCommandToRecipe;
	
	public CategoryCommandToCategory(RecipeCommandToRecipe recipeCommandToRecipe) {
		this.recipeCommandToRecipe = recipeCommandToRecipe;
	}

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCommand source) {
		if (source == null) {
			return null;
		}
		
		final Category category = new Category();
		category.setDescription(source.getDescription());
		category.setId(source.getId());
		source.getRecipes().forEach(
				recipe -> category.getRecipes().add(recipeCommandToRecipe.convert((recipe))));
		
		return category;
	}

}
