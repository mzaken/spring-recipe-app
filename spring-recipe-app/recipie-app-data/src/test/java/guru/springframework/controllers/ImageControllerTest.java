package guru.springframework.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;

public class ImageControllerTest {
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	ImageService imageService;
	
	ImageController imageController;
	
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageController = new ImageController(recipeService, imageService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
	}

	@Test
	public void testShowImageForm() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUploadImage() {
		//fail("Not yet implemented");
	}

}
