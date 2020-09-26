/**
 * 
 */
package guru.springframework.services.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.IngredientService;

/**
 * @author Maor Zaken Created on Feb 1, 2020
 */

public class IngredientServiceJPATest {

	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	IngredientRepository ingredientRepository;
	
	@Mock
	UnitOfMeasureRepository uomRepository;
	
	IngredientService ingredientService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceJPA (
				recipeRepository, 
				uomRepository,
				ingredientRepository);
	}
	
	@Test
	public void findIngredientById() {
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
		
		when(ingredientRepository.findById(anyLong())).thenReturn(Optional.of(ingredient3));
		
		//when
		Ingredient ingredient = ingredientService.getIngredientById(3L);
		
		//then
		assertEquals(3L, ingredient.getId());
		assertEquals(1L, ingredient.getRecipe().getId());
	}
	
	@Test
	public void deleteById() {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Ingredient ingredient1 = new Ingredient();
	    ingredient1.setId(1L);
	    recipe.addIngredient(ingredient1);
	    ingredient1.setRecipe(recipe);
	    
	    Optional<Ingredient> ingredientOptional = Optional.of(ingredient1);
	    
	    when(ingredientRepository.findById(anyLong())).thenReturn(ingredientOptional);
	    
	    //when
	    ingredientService.deleteById(1L);
	    
	    //then
	    verify(ingredientRepository, times(1)).deleteById(anyLong());
	}
	
}
