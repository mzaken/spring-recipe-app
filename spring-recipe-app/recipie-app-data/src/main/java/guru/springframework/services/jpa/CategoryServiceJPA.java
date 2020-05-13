package guru.springframework.services.jpa;

import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.model.Category;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.services.CategoryService;

@Service
public class CategoryServiceJPA implements CategoryService{

	private CategoryRepository categoryRepository;
	
	public CategoryServiceJPA(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Set<Category> getAll() {

		Spliterator<Category> spliterator = categoryRepository.findAll().spliterator();
		return StreamSupport.stream(spliterator, false).collect(Collectors.toSet());
	}
	
}
