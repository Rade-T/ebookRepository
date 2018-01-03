package ebookRepository.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebookRepository.converter.CategoryDTOtoCategory;
import ebookRepository.converter.CategoryToCategoryDTO;
import ebookRepository.dto.CategoryDTO;
import ebookRepository.model.Category;
import ebookRepository.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryDTOtoCategory toCategory;
	
	@Autowired
	private CategoryToCategoryDTO toCategoryDTO;
	
	@RequestMapping(value="getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getCategories() {

		List<CategoryDTO> categories = toCategoryDTO.convert(categoryService.findAll());

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
		CategoryDTO category = toCategoryDTO.convert(categoryService.findOne(id));
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
		
		Category newCategory = categoryService.save(toCategory.convert(categoryDTO));
		return new ResponseEntity<>(toCategoryDTO.convert(newCategory), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
		CategoryDTO deleted = toCategoryDTO.convert(categoryService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}
