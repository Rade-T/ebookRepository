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

import ebookRepository.converter.EbookDTOtoEbook;
import ebookRepository.converter.EbookToEbookDTO;
import ebookRepository.dto.EbookDTO;
import ebookRepository.model.Ebook;
import ebookRepository.service.EbookService;

@RestController
@RequestMapping("/api/ebooks")
public class EbookController {

	@Autowired
	private EbookService ebookService;
	
	@Autowired
	private EbookDTOtoEbook toEbook;
	
	@Autowired
	private EbookToEbookDTO toEbookDTO;
	
	@RequestMapping(value="getEbooks", method = RequestMethod.GET)
	public ResponseEntity<List<EbookDTO>> getEbooks() {

		List<EbookDTO> ebooks = toEbookDTO.convert(ebookService.findAll());

		return new ResponseEntity<>(ebooks, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EbookDTO> getEbook(@PathVariable Long id) {
		EbookDTO ebook = toEbookDTO.convert(ebookService.findOne(id));
		if (ebook == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(ebook, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<EbookDTO> saveEbook(@RequestBody EbookDTO ebookDTO) {
		
		Ebook newEbook = ebookService.save(toEbook.convert(ebookDTO));
		return new ResponseEntity<>(toEbookDTO.convert(newEbook), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EbookDTO> delete(@PathVariable Long id) {
		EbookDTO deleted = toEbookDTO.convert(ebookService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}