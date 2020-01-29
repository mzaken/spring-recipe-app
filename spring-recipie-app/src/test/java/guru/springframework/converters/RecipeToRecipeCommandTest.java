/**
 * 
 */
package guru.springframework.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class RecipeToRecipeCommandTest {

	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPTION = "description";
	public static final Integer PREPTIME = Integer.valueOf("5");
	public static final Integer COOKTIME = Integer.valueOf("10");
	public static final Integer SERVINGS = Integer.valueOf("3");
	public static final String SOURCE = "source";
	public static final String URL = "url";
	public static final String DIRECTIONS = "directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID_2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    
    RecipeToRecipeCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(
				new CategoryToCategoryCommand(), new  NotesToNotesCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Recipe()));
	}
	
	@Test
	public void convert() {
		//given
		Recipe recipe = new Recipe();
		recipe.setId(ID_VALUE);
		recipe.setDescription(DESCRIPTION);
		recipe.setPrepTime(PREPTIME);
		recipe.setCookTime(COOKTIME);
		recipe.setServings(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);
		recipe.setDirections(DIRECTIONS);
		recipe.setDifficulty(DIFFICULTY);
		
		Notes notes = new Notes();
		notes.setId(NOTES_ID);
		
		recipe.setNotes(notes);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(INGRED_ID_1);

		Ingredient ingredient2 = new Ingredient();
		ingredient1.setId(INGRED_ID_2);
		
		recipe.getIngredients().add(ingredient1);
		recipe.getIngredients().add(ingredient2);
		
		Category category1 = new Category();
		category1.setId(CAT_ID_1);
		
		Category category2 = new Category();
		category1.setId(CAT_ID_2);
		
		recipe.getCategories().add(category1);
		recipe.getCategories().add(category2);
		
		//when
		RecipeCommand recipeCommand = converter.convert(recipe);
		
		//then
		assertNotNull(recipeCommand);
		assertEquals(ID_VALUE, recipeCommand.getId());
		assertEquals(DESCRIPTION, recipeCommand.getDescription());
		assertEquals(PREPTIME, recipeCommand.getPrepTime());
		assertEquals(COOKTIME, recipeCommand.getCookTime());
		assertEquals(SERVINGS, recipeCommand.getServings());
		assertEquals(SOURCE, recipeCommand.getSource());
		assertEquals(URL, recipeCommand.getUrl());
		assertEquals(DIRECTIONS, recipeCommand.getDirections());
		assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
		assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
		assertEquals(2, recipeCommand.getCategories().size());
		assertEquals(2, recipeCommand.getIngredients().size());
	}

}
