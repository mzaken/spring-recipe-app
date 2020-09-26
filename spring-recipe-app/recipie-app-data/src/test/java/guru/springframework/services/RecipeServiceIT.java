/**
 * 
 */
package guru.springframework.services;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.model.Recipe;
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

	@Transactional
	@Test
	public void test() {
		//given
		Iterable<Recipe> recipes = recipeRepository.findAll();
		Recipe recipe = recipes.iterator().next();
		
		//when
		recipe.setDescription(DESCRIPTION);
		Recipe savedRecipe= recipeService.save(recipe);
		
		//then
		assertEquals(DESCRIPTION, savedRecipe.getDescription());
		assertEquals(recipe.getId(), savedRecipe.getId());
		assertEquals(recipe.getCategories().size(), savedRecipe.getCategories().size());
		assertEquals(recipe.getIngredients().size(), savedRecipe.getIngredients().size());
	}

}
