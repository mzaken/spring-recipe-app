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
import guru.springframework.domain.Category;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class CategoryToCategoryCommandTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPTION = "description";
	CategoryToCategoryCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void convert() {
		//given
		Category category = new Category();
		category.setDescription(DESCRIPTION);
		category.setId(ID_VALUE);
		
		CategoryCommand categoryCommand = new CategoryCommand();
		//when
		categoryCommand = converter.convert(category);
		
		//then
		assertEquals(ID_VALUE, categoryCommand.getId());
		assertEquals(DESCRIPTION, categoryCommand.getDescription());
	}

}
