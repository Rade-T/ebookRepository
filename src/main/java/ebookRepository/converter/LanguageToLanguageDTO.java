package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.LanguageDTO;
import ebookRepository.model.Language;

public class LanguageToLanguageDTO implements Converter<Language, LanguageDTO> {

	@Override
	public LanguageDTO convert(Language arg0) {
		LanguageDTO l = new LanguageDTO();
		
		l.setId(arg0.getId());
		l.setName(arg0.getName());
		
		return l;
	}
	
	public List<LanguageDTO> convert(List<Language> languages) {
		List<LanguageDTO> retval = new ArrayList<>();
		
		for (Language language : languages) {
			retval.add(convert(language));
		}
		
		return retval;
	}

}
