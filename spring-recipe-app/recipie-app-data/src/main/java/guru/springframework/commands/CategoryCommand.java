/**
 * 
 */
package guru.springframework.commands;

import java.util.HashSet;
import java.util.Set;

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
public class CategoryCommand {
	private Long id;
	private String description;
	private Set<RecipeCommand> recipes = new HashSet<>();
}
