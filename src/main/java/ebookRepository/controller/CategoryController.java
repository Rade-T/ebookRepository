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

import ebookRepository.model.Category;
import ebookRepository.service.CategoryService;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
//	@Autowired
//	private CategoryDTOtoCategory toCategory;
//	
//	@Autowired
//	private CategoryToCategoryDTO toCategoryDTO;
	
	@RequestMapping(value="/getCategories", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategories() {

		List<Category> categories = categoryService.findAll();

		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
		Category category = categoryService.findOne(id);
		if (category == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		
		Category newCategory = categoryService.save(category);
		return new ResponseEntity<>(newCategory, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> delete(@PathVariable Long id) {
		Category deleted = categoryService.delete(id);

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}
