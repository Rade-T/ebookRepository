package ebookRepository.service;

import java.util.List;

import ebookRepository.model.Language;

public interface LanguageService {

	Language findOne(Long id);
	
	List<Language> findAll();
	
	Language save(Language language);
	
	List<Language> save(List<Language> languages);
	
	Language delete(Long id);
	
	List<Language> delete(List<Language> languages);
}
