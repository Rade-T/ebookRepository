package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.CategoryDTO;
import ebookRepository.model.Category;

public class CategoryDTOtoCategory implements Converter<CategoryDTO, Category> {

	@Override
	public Category convert(CategoryDTO arg0) {
		Category c = new Category();
		c.setId(arg0.getId());
		c.setName(arg0.getName());
		return c;
	}
	
	public List<Category> convert(List<CategoryDTO> categories) {
		List<Category> retval = new ArrayList<>();
		for (CategoryDTO c : categories) {
			retval.add(convert(c));
		}
		return retval;
	}
}
