/**
 * 
 */
package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;

/**
 * @author Maor Zaken
 * Created on Feb 1, 2020
 */
public interface IngredientService {
	
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);
	
	Ingredient saveIngredient(Ingredient ingredient);

	void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	Ingredient getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	Ingredient getIngredientById(Long ingredientId);
}
