/**
 * 
 */
package guru.springframework.commands;

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
public class UnitOfMeasureCommand {
	private Long id;
	private String description;
}
