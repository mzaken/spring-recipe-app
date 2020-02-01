/**
 * 
 */
package guru.springframework.services.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.IngredientService;

/**
 * @author Maor Zaken Created on Feb 1, 2020
 */
public class IngredientServiceJPATest {

	@Mock
	RecipeRepository recipeRepository;
	
	IngredientService ingredientService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceJPA(recipeRepository, new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
	}

	@Test
	public void testFindByRecipeIdAndIngredientId() {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Ingredient ingredient1 = new Ingredient();
	    ingredient1.setId(1L);

	    Ingredient ingredient2 = new Ingredient();
	    ingredient2.setId(1L);

	    Ingredient ingredient3 = new Ingredient();
	    ingredient3.setId(3L);
	    
		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
		
		//when
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);
		
		//then
		assertEquals(3L, ingredientCommand.getId());
		assertEquals(1L, ingredientCommand.getRecipeId());
	}

}
