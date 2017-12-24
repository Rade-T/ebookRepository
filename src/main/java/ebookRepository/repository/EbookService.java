package ebookRepository.repository;

import java.util.List;

import ebookRepository.model.Ebook;

public interface EbookService {
	
	Ebook findOne(Long id);
	
	List<Ebook> findAll();
	
	Ebook save(Ebook ebook);
	
	List<Ebook> save(List<Ebook> ebooks);
	
	Ebook delete(Long id);
	
	List<Ebook> delete(List<Ebook> ebooks);
}
