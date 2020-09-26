/**
 * 
 */
package guru.springframework.services.jpa;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
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
	private final UnitOfMeasureRepository uomRepository;
	private final IngredientRepository ingredientRepository;
	
	public IngredientServiceJPA(RecipeRepository recipeRepository,
			 UnitOfMeasureRepository uomRepository,
			IngredientRepository ingredientRepository) {
		this.recipeRepository = recipeRepository;
		this.uomRepository = uomRepository;
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public Ingredient getIngredientById(Long ingredientId) {
		Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
		
		if (!ingredientOptional.isPresent()) {
			//TODO: handle error
			log.error("Could not find ingredient with id: " + ingredientId);
			return null;
		}
		
		return ingredientOptional.get();
	}

	@Override
	public Ingredient saveIngredient(Ingredient ingredient) {
		
		return ingredientRepository.save(ingredient);
	}
	
	@Override
	public Ingredient getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if (!recipeOptional.isPresent()) {
			//TODO: add error handling
			log.error("Could not find Recipe with recipeId: " + recipeId);
		}
		
		Recipe recipe = recipeOptional.get();
		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId)).findFirst();
		
		if (!ingredientOptional.isPresent()) {
			//TODO: add error handling
			log.error("Could not find ingredient with id: " + ingredientId + " for recipe with recipeId: " + recipeId);
		}
		
		return ingredientOptional.get();
	}

	@Override
	public void deleteById(Long ingredientId) {

		ingredientRepository.deleteById(ingredientId);
	}
	
	
	
}