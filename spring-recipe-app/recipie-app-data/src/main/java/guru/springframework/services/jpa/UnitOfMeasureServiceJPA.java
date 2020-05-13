package guru.springframework.services.jpa;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.UnitOfMeasureService;

@Service
public class UnitOfMeasureServiceJPA implements UnitOfMeasureService {
	
	private final UnitOfMeasureRepository uomRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand;
	
	public UnitOfMeasureServiceJPA(UnitOfMeasureRepository uomRepository,
			UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand) {
		this.uomRepository = uomRepository;
		this.uomToUomCommand = uomToUomCommand;
	}

	@Override
	public Optional<UnitOfMeasure> findByDescription(String string) {

		return uomRepository.findByDescription(string);
	}
	
	@Override
	public Set<UnitOfMeasureCommand> getAllUomCommands() {
		return  StreamSupport.stream(uomRepository.findAll().spliterator(), false)
				.map(uomToUomCommand::convert)
				.collect(Collectors.toSet());
	}

	@Override
	public Set<UnitOfMeasure> getAllUom() {
		return StreamSupport.stream(uomRepository.findAll().spliterator(), false).collect(Collectors.toSet());
	}
	
	
}
