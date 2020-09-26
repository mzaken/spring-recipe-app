/**
* 
*/
package guru.springframework.services.jpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Maor Zaken Created on Jan 18, 2020
 */
@Slf4j
@Service
public class RecipeServiceJPA implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceJPA(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	public Recipe findById(Long id) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(id);

		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Missing Recipe!");
		}

		return recipeOptional.get();
	}

	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes;
	}

	@Override
	@Transactional
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}
}
