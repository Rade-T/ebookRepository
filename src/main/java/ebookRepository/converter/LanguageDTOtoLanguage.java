package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ebookRepository.dto.LanguageDTO;
import ebookRepository.model.Language;

@Component
public class LanguageDTOtoLanguage implements Converter<LanguageDTO, Language> {
	
	@Override
	public Language convert(LanguageDTO arg0) {
		Language l = new Language();
		l.setId(arg0.getId());
		l.setName(arg0.getName());
		return l;
	}
	
	public List<Language> convert(List<LanguageDTO> languages) {
		List<Language> retval = new ArrayList<>();
		
		for (LanguageDTO language : languages) {
			retval.add(convert(language));
		}
		
		return retval;
	}
}
