/**
 * 
 */
package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * @author Maor Zaken
 * Created on Feb 1, 2020
 */
public interface IngredientService {
	
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
