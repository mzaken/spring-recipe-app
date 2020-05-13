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
import guru.springframework.model.Category;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class CategoryCommandToCategoryTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPTION = "description";
	CategoryCommandToCategory converter;

	@Before
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void convert() {
		//given
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setDescription(DESCRIPTION);
		categoryCommand.setId(ID_VALUE);
		
		Category category = new Category();
		//when
		category = converter.convert(categoryCommand);
		
		//then
		assertEquals(ID_VALUE, category.getId());
		assertEquals(DESCRIPTION, category.getDescription());
	}

}
