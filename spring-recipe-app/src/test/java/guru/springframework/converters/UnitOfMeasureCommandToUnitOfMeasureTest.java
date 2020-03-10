/**
 * 
 */
package guru.springframework.converters;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

import static org.junit.Assert.*;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPTION = "description";
	UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	public void convert() {
		//given
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(ID_VALUE);
		uomCommand.setDescription(DESCRIPTION);
		
		//when
		UnitOfMeasure uom = converter.convert(uomCommand);
		
		//then 
		assertEquals(ID_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getDescription());
	}
}
