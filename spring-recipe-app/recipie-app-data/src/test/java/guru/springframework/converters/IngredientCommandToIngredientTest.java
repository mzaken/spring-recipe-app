/**
 * 
 */
package guru.springframework.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.model.Ingredient;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class IngredientCommandToIngredientTest {

	IngredientCommandToIngredient converter;
	private static final Long ID_VALUE = new Long(1L);
	private static final String DESCRIPTION = "description";
	private static final BigDecimal AMOUNT = new BigDecimal(2);
	
	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new IngredientCommand()));
	}
	
	@Test
	public void convert() {
		//given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(ID_VALUE);
		ingredientCommand.setDescription(DESCRIPTION);
		ingredientCommand.setAmount(AMOUNT);
		
		//when
		Ingredient ingredient = converter.convert(ingredientCommand);

		//then
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(AMOUNT, ingredient.getAmount());
	}

}
