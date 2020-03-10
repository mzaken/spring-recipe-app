/**
 * 
 */
package guru.springframework.commands;

import java.math.BigDecimal;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
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
public class IngredientCommand {
	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand uom;
	private Long recipeId;
}
