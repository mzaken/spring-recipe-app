/**
 * 
 */
package guru.springframework.services;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;

/**
 * @author Maor Zaken Created on 27 Jan 2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {
	
	public static final String DESCRIPTION = "DESCRIPTION"; 

	@Autowired
	RecipeService recipeService;

	@Autowired
	RecipeRepository recipeRepository;

	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Transactional
	@Test
	public void test() {
		//given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe recipe = recipes.iterator().next();
		RecipeCommand command = recipeToRecipeCommand.convert(recipe);
		
		//when
		command.setDescription(DESCRIPTION);
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		//then
		assertEquals(DESCRIPTION, savedCommand.getDescription());
		assertEquals(recipe.getId(), savedCommand.getId());
		assertEquals(recipe.getCategories().size(), savedCommand.getCategories().size());
		assertEquals(recipe.getIngredients().size(), savedCommand.getIngredients().size());
	}

}
