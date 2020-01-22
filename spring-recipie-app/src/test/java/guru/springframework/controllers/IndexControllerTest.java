package guru.springframework.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import guru.springframework.services.RecipeService;

public class IndexControllerTest {
	
	@Mock
	Model model;
	
	@Mock
	RecipeService recipeService;
	
	IndexController controller;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(recipeService);
	}

	@Test
	public void showIndexPage() {
		String viewName = controller.showIndexPage(model);
		assertEquals("index", viewName);
		verify(recipeService, times(1)).findAll();
		verify(model, times(1)).addAttribute(eq("recipes"), anySet())
		
	}

}