package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.CategoryDTO;
import ebookRepository.model.Category;

public class CategoryToCategoryDTO implements Converter<Category, CategoryDTO> {

	@Override
	public CategoryDTO convert(Category arg0) {
		CategoryDTO c = new CategoryDTO();
		c.setId(arg0.getId());
		c.setName(arg0.getName());
		return c;
	}

	public List<CategoryDTO> convert(List<Category> categories) {
		List<CategoryDTO> retval = new ArrayList<>();
		for (Category c : categories) {
			retval.add(convert(c));
		}
		return retval;
	}
}
