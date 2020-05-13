package guru.springframework.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.model.Category;
import guru.springframework.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("")
	public ResponseEntity<Set<Category>> getAll() {
		Set<Category> categories = categoryService.getAll();
		if (categories.isEmpty()) {
			return new ResponseEntity<Set<Category>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Set<Category>>(categories, HttpStatus.OK);
	}
}
