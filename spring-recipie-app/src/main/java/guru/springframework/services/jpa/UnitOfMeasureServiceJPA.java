package guru.springframework.services.jpa;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.UnitOfMeasureService;

@Service
public class UnitOfMeasureServiceJPA implements UnitOfMeasureService {
	
	private final UnitOfMeasureRepository uomRepository;
	
	public UnitOfMeasureServiceJPA(UnitOfMeasureRepository uomRepository) {
		this.uomRepository = uomRepository;
	}

	@Override
	public Iterable<UnitOfMeasure> findAll() {
		
		return uomRepository.findAll();
	}

	@Override
	public Optional<UnitOfMeasure> findById(Long id) {
		
		return findById(id);
	}

	@Override
	public UnitOfMeasure save(UnitOfMeasure object) {
		
		return uomRepository.save(object);
	}

	@Override
	public void delete(UnitOfMeasure object) {
		
		uomRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		
		uomRepository.deleteById(id);
	}

	@Override
	public Optional<UnitOfMeasure> findByDescription(String string) {

		return uomRepository.findByDescription(string);
	}
	

}
