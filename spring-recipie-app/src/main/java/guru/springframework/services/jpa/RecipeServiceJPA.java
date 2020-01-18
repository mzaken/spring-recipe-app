/**
 * 
 */
package guru.springframework.services.jpa;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.RecipeService;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */

@Service
public class RecipeServiceJPA implements RecipeService {
	
	private final RecipeRepository recipeRepository;
	
	/**
	 * @param recipeRepository
	 */
	public RecipeServiceJPA(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Iterable<Recipe> findAll() {
		
		return recipeRepository.findAll();
	}

	@Override
	public Optional<Recipe> findById(Long id) {
		return recipeRepository.findById(id);
	}

	@Override
	public Recipe save(Recipe object) {
		
		return recipeRepository.save(object);
	}

	@Override
	public void delete(Recipe object) {
		
		recipeRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		
		recipeRepository.deleteById(id);
	}

}
