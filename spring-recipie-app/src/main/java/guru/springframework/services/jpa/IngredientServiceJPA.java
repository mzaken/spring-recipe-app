/**
 * 
 */
package guru.springframework.services.jpa;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.IngredientService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Maor Zaken
 * Created on Feb 1, 2020
 */
@Slf4j
@Service
public class IngredientServiceJPA implements IngredientService{

	private final RecipeRepository recipeRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	
	public IngredientServiceJPA(RecipeRepository recipeRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand) {
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if (!recipeOptional.isPresent()) {
			//TODO: add error handling
			log.error("Could not find Recipe with recipeId: " + recipeId);
		}
		
		Recipe recipe = recipeOptional.get();
		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst()
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient));
		
		if (!ingredientCommandOptional.isPresent()) {
			//TODO: add error handling
			log.error("Could not find ingredient with id: " + ingredientId + " for recipe with recipeId: " + recipeId);
		}
		
		return ingredientCommandOptional.get();
	}
}