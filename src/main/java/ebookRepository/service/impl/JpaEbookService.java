package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.Ebook;
import ebookRepository.repository.EbookRepository;
import ebookRepository.service.EbookService;

@Transactional
@Service
public class JpaEbookService implements EbookService {
	
	@Autowired
	private EbookRepository ebookRepository;

	@Override
	public Ebook findOne(Long id) {
		return ebookRepository.findOne(id);
	}

	@Override
	public List<Ebook> findAll() {
		return ebookRepository.findAll();
	}

	@Override
	public Ebook save(Ebook ebook) {
		return ebookRepository.save(ebook);
	}

	@Override
	public List<Ebook> save(List<Ebook> ebooks) {
		return ebookRepository.save(ebooks);
	}

	@Override
	public Ebook delete(Long id) {
		Ebook ebook = findOne(id);
		ebookRepository.delete(ebook);
		return ebook;
	}

	@Override
	public List<Ebook> delete(List<Ebook> ebooks) {
		ebookRepository.delete(ebooks);
		return null;
	}

}
