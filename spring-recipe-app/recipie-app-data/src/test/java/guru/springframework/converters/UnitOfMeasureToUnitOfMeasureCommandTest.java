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
public class UnitOfMeasureToUnitOfMeasureCommandTest {

	public static final Long ID_VALUE = new Long(1L);
	public static final String DESCRIPTION = "description";
	UnitOfMeasureToUnitOfMeasureCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void convert() {
		//given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(ID_VALUE);
		uom.setDescription(DESCRIPTION);
		
		//when
		UnitOfMeasureCommand uomCommand = converter.convert(uom);
		
		//then 
		assertEquals(ID_VALUE, uomCommand.getId());
		assertEquals(DESCRIPTION, uomCommand.getDescription());
	}
}
