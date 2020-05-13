/**
 * 
 */
package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import lombok.Synchronized;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
	private final UnitOfMeasureCommandToUnitOfMeasure uomCommandToUom;
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomCommandToUom) {
		this.uomCommandToUom = uomCommandToUom;
	}

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) {
			return null;
		}
		
		Ingredient ingredient = new Ingredient();
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setId(source.getId());
		ingredient.setUom(uomCommandToUom.convert(source.getUom()));
		
        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }
		
		return ingredient;
	}

}
