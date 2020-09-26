/**
 * 
 */
package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.model.Ingredient;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;

/**
 * @author Maor Zaken
 * Created on Jan 31, 2020
 */
public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;
	
	@Mock
	UnitOfMeasureService uomService;
	
	@Mock
	Model model;
	
	MockMvc mockMvc;
	
	IngredientController ingredientController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService, ingredientService, uomService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}
	
	@Test
	public void testShowIngredients() throws Exception {
		Ingredient ingredient = new Ingredient();
		//given
		when(ingredientService.getIngredientById((anyLong()))).thenReturn(ingredient);
		
		//when 
		mockMvc.perform(get("/ingredients/1"))
				.andExpect(status().isOk());
		
		//then
		verify(ingredientService, times(1)).getIngredientById(anyLong());
	}
	
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		//given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		
		when(ingredientService.saveIngredient((any()))).thenReturn(ingredient);
		
		//when
		mockMvc.perform(put("/ingredients")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", ""))
				.andExpect(status().isOk());
			
		//then
		verify(ingredientService, times(1)).saveIngredient(any());
	}
	
	@Test
	public void testDeleteIngredient() throws Exception {
		//given
		
		mockMvc.perform(delete("/ingredients/1"))
				.andExpect(status().isOk());
		
		verify(ingredientService, times(1)).deleteById(anyLong());
	}
	
}