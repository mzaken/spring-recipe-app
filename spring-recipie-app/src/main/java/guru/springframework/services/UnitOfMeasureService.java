package guru.springframework.services;

import java.util.Optional;

import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureService extends CrudService<UnitOfMeasure, Long>{

	Optional<UnitOfMeasure> findByDescription(String string);

}
