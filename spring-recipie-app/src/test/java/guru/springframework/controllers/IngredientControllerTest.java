/**
 * 
 */
package guru.springframework.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
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
		RecipeCommand command = new RecipeCommand();
		//given
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		//when 
		mockMvc.perform(get("/recipe/1/ingredients"))
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}
	
	
	@Test
	public void testUpdateIngredient() throws Exception {
		//given
		RecipeCommand command = new RecipeCommand();
		IngredientCommand ingredientCommand = new IngredientCommand();
		
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
				.thenReturn(ingredientCommand);
		
		//when
		mockMvc.perform(get("/recipe/1/ingredient/1/update"))
				.andExpect(view().name("recipe/ingredient/ingredientform"))
				.andExpect(model().attributeExists("ingredient"))
				.andExpect(status().isOk());
		
		//then
		verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
		
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		//given
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);
		
		when(ingredientService.saveIngredientCommand(any())).thenReturn(command);
		
		//when
		mockMvc.perform(post("/recipe/2/ingredient")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
                .param("description", "some string"))
				.andExpect(view().name("redirect:/recipe/2/ingredient/3/show"))
				.andExpect(status().is3xxRedirection());
			
				
		
		
		//then
		
	}

}