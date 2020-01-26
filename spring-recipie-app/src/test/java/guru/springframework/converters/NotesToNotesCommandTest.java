/**
 * 
 */
package guru.springframework.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
public class NotesToNotesCommandTest {

	NotesToNotesCommand converter;
	private static final Long ID_VALUE = new Long(1L);
	private static final String NOTES = "notes";
	
	@Before
	public void setUp() throws Exception {
		converter = new NotesToNotesCommand();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Notes()));
	}

	@Test
	public void convert() {
		//given
		Notes notes = new Notes();
		notes.setId(ID_VALUE);
		notes.setNotes(NOTES);
		
		//when
		NotesCommand notesCommand = converter.convert(notes);
		
		//then
		assertEquals(ID_VALUE, notesCommand.getId());
		assertEquals(NOTES, notesCommand.getNotes());
	}
}
