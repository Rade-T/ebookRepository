package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.Category;
import ebookRepository.repository.CategoryRepository;
import ebookRepository.service.CategoryService;

@Transactional
@Service
public class JpaCategoryService implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> save(List<Category> categories) {
		return categoryRepository.save(categories);
	}

	@Override
	public Category delete(Long id) {
		Category category = findOne(id);
		categoryRepository.delete(category);
		return category;
	}

	@Override
	public List<Category> delete(List<Category> categories) {
		categoryRepository.delete(categories);
		return null;
	}

}
