/**
 * 
 */
package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>{

	@Synchronized
	@Nullable
	@Override
	public IngredientCommand convert(Ingredient source) {
		if (source == null) {
			return null;
		}
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setDescription(source.getDescription());
		ingredientCommand.setAmount(source.getAmount());
		ingredientCommand.setId(source.getId());
		
		return ingredientCommand;
	}

}
