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

import ebookRepository.converter.LanguageDTOtoLanguage;
import ebookRepository.converter.LanguageToLanguageDTO;
import ebookRepository.dto.LanguageDTO;
import ebookRepository.model.Language;
import ebookRepository.service.LanguageService;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@RequestMapping(value="getLanguages", method = RequestMethod.GET)
	public ResponseEntity<List<LanguageDTO>> getLanguages() {

		List<Language> languages = languageService.findAll();
		List<LanguageDTO> languagesDTO = new ArrayList<>();
		for (Language language : languages) {
			languagesDTO.add(new LanguageDTO(language));
		}
		return new ResponseEntity<>(languagesDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LanguageDTO> getLanguage(@PathVariable Long id) {
		Language language = languageService.findOne(id);
		if (language == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new LanguageDTO(language), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> saveLanguage(@RequestBody LanguageDTO languageDTO) {
		Language l = new Language();
		l.setName(languageDTO.getName());
		languageService.save(l);
		return new ResponseEntity<>("Saved", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		languageService.delete(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}