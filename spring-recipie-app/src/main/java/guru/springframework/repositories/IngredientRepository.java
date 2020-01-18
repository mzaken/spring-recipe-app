/**
 * 
 */
package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Ingredient;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
