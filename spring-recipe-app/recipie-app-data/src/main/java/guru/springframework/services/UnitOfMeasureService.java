package guru.springframework.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.model.UnitOfMeasure;

public interface UnitOfMeasureService {

	Optional<UnitOfMeasure> findByDescription(String string);
	
	Set<UnitOfMeasure> getAllUom();

}
