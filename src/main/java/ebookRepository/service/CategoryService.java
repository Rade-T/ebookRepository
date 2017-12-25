package ebookRepository.service;

import java.util.List;

import ebookRepository.model.Category;

public interface CategoryService {
	
	Category findOne(Long id);
	
	List<Category> findAll();
	
	Category save(Category category);
	
	List<Category> save(List<Category> categories);
	
	Category delete(Long id);
	
	List<Category> delete(List<Category> categories);
}
