/**
 * 
 */
package guru.springframework.services;

import guru.springframework.model.Ingredient;

/**
 * @author Maor Zaken
 * Created on Feb 1, 2020
 */
public interface IngredientService {
	
	Ingredient saveIngredient(Ingredient ingredient);

	Ingredient getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	Ingredient getIngredientById(Long ingredientId);
	
	void deleteById(Long ingredientId);
}
