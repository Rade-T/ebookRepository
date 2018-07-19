package ebookRepository.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebookRepository.dto.CategoryDTO;
import ebookRepository.model.Category;
import ebookRepository.service.CategoryService;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> getCategories() {

		List<Category> categories = categoryService.findAll();
		List<CategoryDTO> categoriesDTO = new ArrayList<CategoryDTO>();
		for (Category category : categories) {
			categoriesDTO.add(new CategoryDTO(category));
		}

		return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
		Category category = categoryService.findOne(id);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
		
		Category category = new Category();
		category.setName(categoryDTO.getName());
		categoryService.save(category);
		return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.POST)
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {

		Category category = new Category();
		category.setId(id);
		category.setName(categoryDTO.getName());
		categoryService.save(category);
		return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		categoryService.delete(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
