/**
 * 
 */
package guru.springframework.services.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
	RecipeRepository recipeRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceJPA(recipeRepository);
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
}
