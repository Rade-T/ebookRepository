package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.EbookDTO;
import ebookRepository.model.Ebook;

public class EbookToEbookDTO implements Converter<Ebook, EbookDTO> {

	@Override
	public EbookDTO convert(Ebook arg0) {
		EbookDTO e = new EbookDTO();
		e.setId(arg0.getId());
		e.setAuthor(arg0.getAuthor());
		e.setFilename(arg0.getFilename());
		e.setKeywords(arg0.getKeywords());
		e.setTitle(arg0.getTitle());
		e.setMIME(arg0.getMIME());
		e.setPublicationYear(arg0.getPublicationYear());
		e.setCataloguerId( arg0.getCataloguer().getId() );
		e.setCategoryId( arg0.getCategory().getId() );
		e.setLanguageId( arg0.getLanguage().getId() );
		return e;
	}
	
	public List<EbookDTO> convert(List<Ebook> ebooks) {
		List<EbookDTO> retval = new ArrayList<>();
		for (Ebook e : ebooks) {
			retval.add(convert(e));
		}
		return retval;
	}

}
