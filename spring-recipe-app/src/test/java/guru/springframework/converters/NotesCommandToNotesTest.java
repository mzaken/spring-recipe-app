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
public class NotesCommandToNotesTest {

	NotesCommandToNotes converter;
	private static final Long ID_VALUE = new Long(1L);
	private static final String NOTES = "notes";
	
	@Before
	public void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new NotesCommand()));
	}

	@Test
	public void convert() {
		//given
		NotesCommand notesCommand = new NotesCommand();
		notesCommand.setId(ID_VALUE);
		notesCommand.setNotes(NOTES);
		
		//when
		Notes notes = converter.convert(notesCommand);
		
		//then
		assertEquals(ID_VALUE, notes.getId());
		assertEquals(NOTES, notes.getNotes());
	}
}
