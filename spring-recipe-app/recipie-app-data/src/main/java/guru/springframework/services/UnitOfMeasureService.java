package guru.springframework.services;

import java.util.Optional;
import java.util.Set;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public interface UnitOfMeasureService {

	Optional<UnitOfMeasure> findByDescription(String string);
	
	Set<UnitOfMeasureCommand> getAllUomCommands();
	
	Set<UnitOfMeasure> getAllUom();

}
