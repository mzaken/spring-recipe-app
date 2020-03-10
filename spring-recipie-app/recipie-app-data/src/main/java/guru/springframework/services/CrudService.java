/**
 * 
 */
package guru.springframework.services;

import java.util.Optional;

/**
 * @author Maor Zaken
 * Created on Jan 18, 2020
 */
public interface CrudService<T, ID> {
	
	Iterable<T> findAll();
	
	Optional<T> findById(ID id);
	
	T save(T object);
	
	void delete(T object);
	
	void deleteById(ID id);
}
