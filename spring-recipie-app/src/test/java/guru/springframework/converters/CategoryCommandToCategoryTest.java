/**
 * 
 */
package guru.springframework.converters;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
class CategoryCommandToCategoryTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPTION = "description";
	CategoryCommandToCategory converter;

	@Before
	void setUp() throws Exception {
		converter = new CategoryCommandToCategory());
	}

	@Test
	void testNullObject() {
		fail("Not yet implemented");
	}
	
	@Test
	void testEmptyObject() {
		
	}

	
	@Test
	void convert() {
		
	}

}
