package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.Language;
import ebookRepository.repository.LanguageRepository;
import ebookRepository.service.LanguageService;

@Transactional
@Service
public class JpaLanguageService implements LanguageService {
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public Language findOne(Long id) {
		return languageRepository.findOne(id);
	}

	@Override
	public List<Language> findAll() {
		return languageRepository.findAll();
	}

	@Override
	public Language save(Language language) {
		return languageRepository.save(language);
	}

	@Override
	public List<Language> save(List<Language> languages) {
		return languageRepository.save(languages);
	}

	@Override
	public Language delete(Long id) {
		Language language = findOne(id);
		if (language == null) {
			throw new IllegalArgumentException("Tried to delete a null language");
		}
		languageRepository.delete(language);
		return language;
	}

	@Override
	public List<Language> delete(List<Language> languages) {
		languageRepository.delete(languages);
		return null;
	}

}
