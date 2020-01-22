/**
 * 
 */
package guru.springframework.services;

import java.util.Set;

import guru.springframework.domain.Recipe;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */

public interface RecipeService extends CrudService<Recipe, Long>{

	public Set<Recipe> getRecipes();
}
