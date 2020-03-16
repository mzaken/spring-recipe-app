/**
 * 
 */
package guru.springframework.services.jpa;

import java.util.Optional;

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
	private final IngredientRepository ingredientRepository;
	
	public IngredientServiceJPA(RecipeRepository recipeRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository uomRepository,
			IngredientRepository ingredientRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.uomRepository = uomRepository;
		this.ingredientRepository = ingredientRepository;
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
			Ingredient ingredient = ingredientCommandToIngredient.convert(command); 
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
		}
		
		Recipe savedRecipe = recipeRepository.save(recipe);
		
		Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
				.filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
				.findFirst();
		
		if (!savedIngredientOptional.isPresent()) {
			
			savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
					.filter(ingredient -> ingredient.getRecipe().getId().equals(command.getRecipeId()))
					.filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
					.findFirst();	
		}
		
		return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
	}
	
	@Override
	public void deleteByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if (!recipeOptional.isPresent()) {
			//TODO - handle error missing recipe.
		}
		
		Recipe recipe = recipeOptional.get();
		
		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.findFirst();
				
		
		if (!ingredientOptional.isPresent()) {
			//TODO: handle error ingredient not found
		}
		
		Ingredient ingredient = ingredientOptional.get();
		
		recipe.getIngredients().remove(ingredient);
		ingredient.setRecipe(null);
		//ingredientRepository.delete(ingredient);
		recipeRepository.save(recipe);
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
	
}