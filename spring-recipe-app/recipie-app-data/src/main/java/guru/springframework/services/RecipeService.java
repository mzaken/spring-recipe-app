/**
 * 
 */
package guru.springframework.services;

import java.util.Set;

import guru.springframework.model.Recipe;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */

public interface RecipeService {

	public Set<Recipe> getRecipes();
		
	public Recipe findById(Long id);

	public Recipe save(Recipe recipe);
	
	public void deleteById(Long id);
}
