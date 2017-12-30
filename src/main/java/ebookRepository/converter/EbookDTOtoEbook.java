package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.EbookDTO;
import ebookRepository.model.Ebook;
import ebookRepository.service.CategoryService;
import ebookRepository.service.LanguageService;
import ebookRepository.service.UserService;

public class EbookDTOtoEbook implements Converter<EbookDTO, Ebook> {
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public Ebook convert(EbookDTO arg0) {
		Ebook e = new Ebook();
		e.setId(arg0.getId());
		e.setAuthor(arg0.getAuthor());
		e.setFilename(arg0.getFilename());
		e.setTitle(arg0.getTitle());
		e.setLanguage( languageService.findOne(arg0.getLanguageId()) );
		e.setKeywords(arg0.getKeywords());
		e.setMIME(arg0.getMIME());
		e.setPublicationYear(arg0.getPublicationYear());
		e.setCataloguer( userService.findOne(arg0.getCataloguerId()) );
		e.setCategory( categoryService.findOne(arg0.getCategoryId()) );
		return e;
	}
	
	public List<Ebook> convert(List<EbookDTO> ebooks) {
		List<Ebook> retval = new ArrayList<>();
		for (EbookDTO e : ebooks) {
			retval.add(convert(e));
		}
		return retval;
	}
}
