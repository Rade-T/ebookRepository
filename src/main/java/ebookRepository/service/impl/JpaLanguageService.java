package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.Language;
import ebookRepository.service.LanguageService;

@Transactional
@Service
public class JpaLanguageService implements LanguageService {

	@Override
	public Language findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Language> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language save(Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Language> save(List<Language> languages) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Language> delete(List<Language> languages) {
		// TODO Auto-generated method stub
		return null;
	}

}
