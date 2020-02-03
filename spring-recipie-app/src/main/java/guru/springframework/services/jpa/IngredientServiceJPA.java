/**
 * 
 */
package guru.springframework.services.jpa;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
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
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	private final UnitOfMeasureRepository uomRepository;
	
	public IngredientServiceJPA(RecipeRepository recipeRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient,
			UnitOfMeasureRepository uomRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.uomRepository = uomRepository;
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

	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
		
		if (!recipeOptional.isPresent()) {
			//TODO: add error handling
			log.error("recipe not found for id: " + command.getRecipeId());
			return new IngredientCommand();
		}
		
		Recipe recipe = recipeOptional.get();
		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId()))
				.findFirst();
		
		if (ingredientOptional.isPresent()) {
			Ingredient foundIngredient = ingredientOptional.get();
			foundIngredient.setAmount(command.getAmount());
			foundIngredient.setDescription(command.getDescription());
			foundIngredient.setUom(uomRepository.findById(command.getUom().getId())
					.orElseThrow(() -> new RuntimeException("UOM not found")));
		} else {
			recipe.addIngredient(ingredientCommandToIngredient.convert(command));
		}
		
		Recipe savedRecipe = recipeRepository.save(recipe);
		
		return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId()))
				.findFirst().get());
	}
	
}