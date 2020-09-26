package guru.springframework.services.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.UnitOfMeasureService;

public class UnitOfMeasureServiceJPATest {
	
	@Mock
	UnitOfMeasureRepository uomRepository;
	
	UnitOfMeasureService service;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		service = new UnitOfMeasureServiceJPA(uomRepository);
	}

	@Test
	public void testFindByDescription() {
		//given
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		uom1.setDescription("testDescription");
		
		when(uomRepository.findByDescription(any())).thenReturn(Optional.of(uom1));
		
		//when 
		Optional<UnitOfMeasure> foundUom = service.findByDescription("testDescription");
		
		//then
		assertEquals("testDescription", foundUom.get().getDescription());
		verify(uomRepository, times(1)).findByDescription(any());
	}

	@Test
	public void getAllUomCommands() throws Exception {
		//given
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		
		Set<UnitOfMeasure> uoms = new HashSet<>();
		uoms.add(uom1);
		uoms.add(uom2);
		
		when(uomRepository.findAll()).thenReturn(uoms);
		
		//when
		Set<UnitOfMeasure> allUom = service.getAllUom();
		
		//then
		assertEquals(2, allUom.size());
		verify(uomRepository, times(1)).findAll();
	}

}
