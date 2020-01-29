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
import guru.springframework.domain.Ingredient;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class IngredientToIngredientCommandTest {

	IngredientToIngredientCommand converter;
	private static final Long ID_VALUE = new Long(1L);
	private static final String DESCRIPTION = "description";
	private static final BigDecimal AMOUNT = new BigDecimal(2);
	
	@Before
	public void setUp() throws Exception {
		converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Ingredient()));
	}
	
	@Test
	public void convert() {
		//given
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ID_VALUE);
		ingredient.setDescription(DESCRIPTION);
		ingredient.setAmount(AMOUNT);
		
		//when
		IngredientCommand ingredientCommand = converter.convert(ingredient);

		//then
		assertEquals(ID_VALUE, ingredientCommand.getId());
		assertEquals(DESCRIPTION, ingredientCommand.getDescription());
		assertEquals(AMOUNT, ingredientCommand.getAmount());
	}

}
