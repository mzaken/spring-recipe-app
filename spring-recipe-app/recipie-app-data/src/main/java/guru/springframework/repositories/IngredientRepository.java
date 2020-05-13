/**
 * 
 */
package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.model.Ingredient;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
