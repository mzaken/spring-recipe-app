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
import guru.springframework.model.Difficulty;
import guru.springframework.model.Recipe;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class RecipeCommandToRecipeTest {

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
    
    RecipeCommandToRecipe converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new RecipeCommandToRecipe
				(new CategoryCommandToCategory(), new NotesCommandToNotes(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new RecipeCommand()));
	}
	
	@Test
	public void convert() {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(ID_VALUE);
		recipeCommand.setDescription(DESCRIPTION);
		recipeCommand.setPrepTime(PREPTIME);
		recipeCommand.setCookTime(COOKTIME);
		recipeCommand.setServings(SERVINGS);
		recipeCommand.setSource(SOURCE);
		recipeCommand.setUrl(URL);
		recipeCommand.setDirections(DIRECTIONS);
		recipeCommand.setDifficulty(DIFFICULTY);
		
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(NOTES_ID);
		
		recipeCommand.setNotes(notesCommand);
		
		IngredientCommand ingredient1 = new IngredientCommand();
		ingredient1.setId(INGRED_ID_1);

		IngredientCommand ingredient2 = new IngredientCommand();
		ingredient1.setId(INGRED_ID_2);
		
		recipeCommand.getIngredients().add(ingredient1);
		recipeCommand.getIngredients().add(ingredient2);
		
		CategoryCommand category1 = new CategoryCommand();
		category1.setId(CAT_ID_1);
		
		CategoryCommand category2 = new CategoryCommand();
		category1.setId(CAT_ID_2);
		
		recipeCommand.getCategories().add(category1);
		recipeCommand.getCategories().add(category2);
		
		//when
		Recipe recipe = converter.convert(recipeCommand);
		
		//then
		assertNotNull(recipe);
		assertEquals(ID_VALUE, recipe.getId());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(PREPTIME, recipe.getPrepTime());
		assertEquals(COOKTIME, recipe.getCookTime());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());
		assertEquals(DIRECTIONS, recipe.getDirections());
		assertEquals(DIFFICULTY, recipe.getDifficulty());
		assertEquals(NOTES_ID, recipe.getNotes().getId());
		assertEquals(2, recipe.getCategories().size());
		assertEquals(2, recipe.getIngredients().size());
	}

}
