/**
 * 
 */
package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;

/**
 * @author Maor Zaken
 * Created on 26 Jan 2020
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{
	
	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {
		if (source == null) {
			return null;
		}
		
		final CategoryCommand category = new CategoryCommand();
		category.setDescription(source.getDescription());
		category.setId(source.getId());
		
		return category;
	}

}
