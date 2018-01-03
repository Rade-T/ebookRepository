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
	
	@Autowired
	private LanguageDTOtoLanguage toLanguage;
	
	@Autowired
	private LanguageToLanguageDTO toLanguageDTO;
	
	@RequestMapping(value="getLanguages", method = RequestMethod.GET)
	public ResponseEntity<List<LanguageDTO>> getLanguages() {

		List<LanguageDTO> languages = toLanguageDTO.convert(languageService.findAll());

		return new ResponseEntity<>(languages, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<LanguageDTO> getLanguage(@PathVariable Long id) {
		LanguageDTO language = toLanguageDTO.convert(languageService.findOne(id));
		if (language == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(language, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<LanguageDTO> saveLanguage(@RequestBody LanguageDTO languageDTO) {
		
		Language newLanguage = languageService.save(toLanguage.convert(languageDTO));
		return new ResponseEntity<>(toLanguageDTO.convert(newLanguage), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LanguageDTO> delete(@PathVariable Long id) {
		LanguageDTO deleted = toLanguageDTO.convert(languageService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}