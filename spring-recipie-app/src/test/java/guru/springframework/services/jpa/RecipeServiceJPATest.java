/**
 * 
 */
package guru.springframework.services.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */


class RecipeServiceJPATest {
	
	RecipeService recipeService;
	
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Mock
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceJPA(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	void findById() {
		Recipe recipe = new Recipe();
		recipe.setId(1l);
		
		when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
		assertNotNull(recipeService.findById(1l));
	}
	
	@Test 
	void getRecipes() {
		Recipe recipe1 = new Recipe();
		Recipe recipe2 = new Recipe();
		recipe1.setId(1l);
		recipe2.setId(2l);
		
		Set<Recipe> recipes = new HashSet<>();
		recipes.add(recipe1);
		recipes.add(recipe2);
		
		when(recipeRepository.findAll()).thenReturn(recipes);
		
		assertNotNull(recipeService.getRecipes());
		assertEquals(2, recipeService.getRecipes().size());
	}
	
    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
    
    public void deleteById() {
    	//given 
    	Recipe recipe = new Recipe();
    	recipe.setId(2L);
    	
    	//when
    	recipeService.deleteById(2L);
    	
    	//then
    	verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}
