/**
 * 
 */
package guru.springframework.commands;

import guru.springframework.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Getter
@Setter
@NoArgsConstructor
public class NotesCommand {
	private Long id;
	private String notes;
	private RecipeCommand recipe;
}
