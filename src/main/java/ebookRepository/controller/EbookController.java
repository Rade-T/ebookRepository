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

import ebookRepository.dto.EbookDTO;
import ebookRepository.model.Ebook;
import ebookRepository.service.CategoryService;
import ebookRepository.service.EbookService;
import ebookRepository.service.LanguageService;
import ebookRepository.service.UserService;

@RestController
@RequestMapping("/api/ebooks")
public class EbookController {

	@Autowired
	private EbookService ebookService;
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EbookDTO>> getEbooks() {

		List<Ebook> ebooks = ebookService.findAll();
		List<EbookDTO> ebooksDTO = new ArrayList<>();
		for (Ebook ebook : ebooks) {
			ebooksDTO.add(new EbookDTO(ebook));
		}

		return new ResponseEntity<>(ebooksDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EbookDTO> getEbook(@PathVariable Long id) {
		Ebook ebook = ebookService.findOne(id);
		if (ebook == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		EbookDTO ebookDTO = new EbookDTO(ebook);
		return new ResponseEntity<>(ebookDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<EbookDTO> saveEbook(@RequestBody EbookDTO ebookDTO) {
		Ebook ebook = new Ebook();
		ebook.setTitle(ebookDTO.getTitle());
		ebook.setAuthor(ebookDTO.getAuthor());
		ebook.setKeywords(ebookDTO.getKeywords());
		ebook.setFilename(ebookDTO.getFilename());
		ebook.setMIME(ebookDTO.getMIME());
		ebook.setPublicationYear(ebookDTO.getPublicationYear());
		ebook.setCataloguer(userService.findOne(ebookDTO.getCataloguer()));
		ebook.setCategory(categoryService.findOne(ebookDTO.getCategory()));
		ebook.setLanguage(languageService.findOne(ebookDTO.getLanguage()));
		ebookService.save(ebook);
		return new ResponseEntity<>(new EbookDTO(ebook), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.POST)
	public ResponseEntity<EbookDTO> updateEbook(@RequestBody EbookDTO ebookDTO, @PathVariable Long id) {
		Ebook ebook = new Ebook();
		ebook.setId(id);
		ebook.setTitle(ebookDTO.getTitle());
		ebook.setAuthor(ebookDTO.getAuthor());
		ebook.setKeywords(ebookDTO.getKeywords());
		ebook.setFilename(ebookDTO.getFilename());
		ebook.setMIME(ebookDTO.getMIME());
        ebook.setPublicationYear(ebookDTO.getPublicationYear());
		ebook.setCataloguer(userService.findOne(ebookDTO.getCataloguer()));
		ebook.setCategory(categoryService.findOne(ebookDTO.getCategory()));
		ebook.setLanguage(languageService.findOne(ebookDTO.getLanguage()));
		ebookService.save(ebook);
		return new ResponseEntity<>(new EbookDTO(ebook), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Long id) {
		ebookService.delete(id);

		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}