/**
 * 
 */
package guru.springframework.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Maor Zaken
 * Created on Apr 4, 2020
 */

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UomController {
	
	private final UnitOfMeasureService uomService;
	
	public UomController(UnitOfMeasureService uomService) {
		this.uomService = uomService;
	}

	@GetMapping("uoms")
	public ResponseEntity<Set<UnitOfMeasure>> getAllUoms() {
		Set<UnitOfMeasure> uoms = uomService.getAllUom();
		
		return ResponseEntity.ok(uoms);
	}
}
