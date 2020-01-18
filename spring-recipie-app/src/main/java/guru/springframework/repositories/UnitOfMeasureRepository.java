/**
 * 
 */
package guru.springframework.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
	
	Optional<UnitOfMeasure> findByDescription(String description);
}
